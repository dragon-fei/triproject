package com.use.util.web.validator;

public class ValueRuleValidator {
    public ValueRuleValidator() {
    }
    public static boolean isString(String str){
        if(str == null || "".equals(str)){
            return false;
        }
        return true;
    }
    public static boolean isBoolean(String str){
        if(isString(str)){
            return "true".equals(str) || "false".equals(str);
        }
        return false;
    }
    public static boolean isRand(String str,String rand){
        if(isString(str)&& isString(rand)){
            return str.equalsIgnoreCase(rand);
        }
        return false;
    }
    public static boolean isDatetime(String str){
        if(isString(str)){
            return str.matches("\\d{4}-\\d{2}-\\d{2}-\\d{2}-\\d{2}-\\d{2}");
        }
        return false;
    }
    public static boolean isDate(String str){
        if(isString(str)){
            return str.matches("\\d{4}-\\d{2}-\\d{2}");
        }
        return false;
    }
    public static boolean isDouble(String str){
        if(isString(str)){
            return str.matches("\\d+(\\.\\d+)?");
        }
        return false;
    }
    public static boolean isLong(String str){
        return isInt(str);
    }
    public static boolean isInt(String str){
        if(isString(str)){
            return str.matches("\\d+");
        }
        return false;
    }
}
