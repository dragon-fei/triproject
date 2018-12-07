package com.use.service.impl;

import com.use.dao.IActionDAO;
import com.use.dao.IEmpDAO;
import com.use.dao.ILevelDAO;
import com.use.dao.IRoleDAO;
import com.use.service.IEmpService;
import com.use.vo.Emp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
@Service
public class EmpServiceImpl implements IEmpService {
    @Resource
    private IEmpDAO empDAO;
    @Resource
    private IRoleDAO roleDAO;
    @Resource
    private IActionDAO actionDAO;
    @Resource
    private ILevelDAO levelDAO;

    @Override
    public Map<String, Object> get(String eid, String password) {
        Map<String,Object> map = new HashMap<String,Object>();
        Emp emp = this.empDAO.findById(eid);
        if(emp != null){
            if(password.equals(emp.getPassword())){
                map.put("level",this.levelDAO.findById(emp.getLid()));
            }
        }
        map.put("emp",emp);
        return map;
    }

    @Override
    public Map<String, Set<String>> listRoleAndAction(String eid) {
        Map<String,Set<String>> map = new HashMap<String,Set<String>>();
        map.put("allRoles",this.roleDAO.findAllIdByEmp(eid));
        map.put("allActions",this.actionDAO.findAllIdByEmp(eid));
        return map;
    }
}
