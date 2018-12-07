package com.use.util.enctype;

import java.util.Base64;

public class PasswordUtil {
    private static final String SEED = "mldnjava";
    private static final int NE_NUM = 3;

    public PasswordUtil() {
    }
    // 创建一个基于Base64的种子数
    private static String createSeed() {
        String str = SEED;
        for(int x = 0; x < NE_NUM;x++) {
            str = Base64.getEncoder().encodeToString(str.getBytes());
        }
        return str;
    }
    //密码加密
    public static String getPassword(String password) {
        MD5Code md5 = new MD5Code();
        String pass = "{" + password + ":" + createSeed() + "}";
        for(int x = 0; x < NE_NUM; x++) {
            pass = md5.getMD5ofStr(pass);
        }
        return pass;
    }
}
