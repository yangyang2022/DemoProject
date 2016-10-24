package com.yangyang.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthProperty {

    @Value("#{setting[admin]}")
    private String adminStr;
    @Value("#{setting[user]}")
    private String userStr;

    public String getAdminStr() {
        return adminStr;
    }

    public void setAdminStr(String adminStr) {
        this.adminStr = adminStr;
    }

    public String getUserStr() {
        return userStr;
    }

    public void setUserStr(String userStr) {
        this.userStr = userStr;
    }
}
