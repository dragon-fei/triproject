package com.use.realm.cre;

import com.use.util.enctype.PasswordUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class CustomerCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Object tokenCredentials = PasswordUtil.getPassword(super.toString(token.getCredentials())).getBytes();
        Object accountCredentials = super.getCredentials(info);
        return super.equals(tokenCredentials, accountCredentials);
    }
}
