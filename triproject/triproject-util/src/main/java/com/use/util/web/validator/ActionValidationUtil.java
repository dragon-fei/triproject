package com.use.util.web.validator;

import org.springframework.context.MessageSource;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionValidationUtil {
    private HttpServletRequest request;
    private String rule;
    private MessageSource messageSource;
    private Map<String,String> errors = new HashMap<String,String>();

    public ActionValidationUtil(HttpServletRequest request, String rule, MessageSource messageSource) {
        this.request = request;
        this.rule = rule;
        this.messageSource = messageSource;
        this.handleValidator();
    }
    private void handleValidator(){
        String ruleResult[] = this.rule.split("\\|");
        for(int x = 0; x < ruleResult.length;x++){
            String temp[] = ruleResult[x].split(":");
            String parameterValue = this.request.getParameter(temp[0]);
            switch (temp[1]){
                case "int" :
                    if(!ValueRuleValidator.isInt(parameterValue)){
                        this.errors.put(temp[0], this.messageSource.getMessage("validation.int.msg", null, null)) ;
                    }
                    break;
                case "long" :
                    if(!ValueRuleValidator.isLong(parameterValue)){
                        this.errors.put(temp[0],this.messageSource.getMessage("validation.long.msg",null,null));
                    }
                    break;
                case "double" :
                    if(!ValueRuleValidator.isDouble(parameterValue)){
                        this.errors.put(temp[0],this.messageSource.getMessage("validation.double.msg",null,null));
                    }
                    break;
                case "string" :
                    if(!ValueRuleValidator.isString(parameterValue)){
                        this.errors.put(temp[0],this.messageSource.getMessage("validation.string.msg",null,null));
                    }
                    break;
                case "date" :
                    if (!ValueRuleValidator.isDate(parameterValue)) {	// 验证不通过
                        // key为参数名称、value为messages中定义的提示文字信息
                        this.errors.put(temp[0], this.messageSource.getMessage("validation.date.msg", null, null)) ;
                    }
                    break ;
                case "datetime" :
                    if (!ValueRuleValidator.isDatetime(parameterValue)) {	// 验证不通过
                        // key为参数名称、value为messages中定义的提示文字信息
                        this.errors.put(temp[0], this.messageSource.getMessage("validation.datetime.msg", null, null)) ;
                    }
                    break ;
                case "rand" :
                    if (!ValueRuleValidator.isRand(parameterValue,(String)this.request.getSession().getAttribute("rand"))) {	// 验证不通过
                        // key为参数名称、value为messages中定义的提示文字信息
                        this.errors.put(temp[0], this.messageSource.getMessage("validation.rand.msg", null, null)) ;
                    }
                    break ;
                case "boolean" :
                    if (!ValueRuleValidator.isBoolean(parameterValue)) {	// 验证不通过
                        // key为参数名称、value为messages中定义的提示文字信息
                        this.errors.put(temp[0], this.messageSource.getMessage("validation.boolean.msg", null, null)) ;
                    }
                    break ;
            }
        }
    }
    public Map<String,String> getErrors() {	// 获取所有的错误信息
        return this.errors ;
    }
}
