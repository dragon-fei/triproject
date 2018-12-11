package com.use.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginAction {
    @RequestMapping("/loginPage")
    public ModelAndView loginPage(){
        ModelAndView mav = new ModelAndView("login");
        System.out.println("******************************loginPage******************");
        return mav;
    }
}
