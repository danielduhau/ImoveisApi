package com.danielduhau.ImoveisApi.security;

public enum ApplicationUserPermission {
    IMOVEL_READ("imovel:read"),
    IMOVEL_WRITE("imovel:write"),
    APPUSER_READ("appuser:read"),
    APPUSER_WRITE("appuser:write");

    private final String permission;

    ApplicationUserPermission(String permission){
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
