package com.syllabus.modulo1_proj.avaliativo.dtoUtils.login;

public class DtoTokenResponse {
    private String token;
    private String role;
    private String nome;

    public DtoTokenResponse() {}
    public DtoTokenResponse(String token, String role, String nome) {
        this.token = token;
        this.role = role;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
