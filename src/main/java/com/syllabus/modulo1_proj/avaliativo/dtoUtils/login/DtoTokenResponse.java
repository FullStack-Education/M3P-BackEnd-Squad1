package com.syllabus.modulo1_proj.avaliativo.dtoUtils.login;

public class DtoTokenResponse {
    private String token;
    private String role;

    public DtoTokenResponse(){}
    public DtoTokenResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public DtoTokenResponse(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
