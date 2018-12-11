package com.use.realm;

import com.use.service.IEmpService;
import com.use.util.enctype.PasswordUtil;
import com.use.vo.Emp;
import com.use.vo.Level;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

public class EmpRealm extends AuthorizingRealm {

    @Resource
    private IEmpService empService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("*********doGetAuthenticationInfo***************");
        String eid = token.getPrincipal().toString();
        System.out.println("********************eid= " + eid);
        String password = PasswordUtil.getPassword(new String((char[])token.getCredentials()));
        System.out.println("************************password= " + password);
        Map<String,Object> map = empService.get(eid,password);
        Emp emp = (Emp) map.get("emp") ; // 通过用户名获取用户信息
        if (emp == null) { // 表示该用户信息不存在，不存在则应该抛出一个异常
            throw new UnknownAccountException("用户名不存在！");
        }
        if (!password.equals(emp.getPassword())) { // 密码验证
            throw new IncorrectCredentialsException("错误的用户名与密码！");
        }
        // 随后还需要考虑用户被锁定的问题
        if (emp.getLocked().equals(1)) { // 1表示非0，非0就是true
            throw new LockedAccountException("账户已被锁定");
        }
        Level level = (Level) map.get("level") ;
        // 定义需要进行返回的操作数据信息项
        SimpleAuthenticationInfo auth = new SimpleAuthenticationInfo(token.getPrincipal(), password,
                "empRealm");
        SecurityUtils.getSubject().getSession().setAttribute("ename", emp.getEname());
        SecurityUtils.getSubject().getSession().setAttribute("photo", emp.getPhoto());
        SecurityUtils.getSubject().getSession().setAttribute("level", level.getLevel());
        return auth;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        System.out.println("**************doGetAuthorizationInfo************************");
        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
        // 执行到此方法的时候一定是已经进行过用户认证处理了（用户名和密码一定是正确的）
        String eid = (String) principals.getPrimaryPrincipal(); // 取得用户名
        System.out.println("**********************eid= " + eid);
        Map<String, Set<String>> map = this.empService.listRoleAndAction(eid);
        auth.setRoles(map.get("allRoles")); // 保存所有的角色
        auth.setStringPermissions(map.get("allActions")); // 保存所有的权限
        return auth;
    }
}
