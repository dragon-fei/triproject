package com.use.action.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CustomerFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String rand = (String)session.getAttribute("rand");
        String code = request.getParameter("code");
        if(rand == null || code == null || "".equals(rand) || "".equals(code)){
            request.setAttribute("code","验证码不能为空");
            return true;
        }else {
            if(!code.equalsIgnoreCase(rand)){
                request.setAttribute("code","验证码输入错误");
                return true;
            }
        }
        return super.onAccessDenied(request, response);
    }
}
