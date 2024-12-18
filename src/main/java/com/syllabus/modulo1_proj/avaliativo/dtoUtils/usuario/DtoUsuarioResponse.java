package com.syllabus.modulo1_proj.avaliativo.dtoUtils.usuario;
import com.syllabus.modulo1_proj.avaliativo.entities.Usuario;
import lombok.AllArgsConstructor;

public class DtoUsuarioResponse {
    private String login;
    private String papel;
    private String usuarioId;

    public DtoUsuarioResponse(){}
    public DtoUsuarioResponse(String login, String senha, String papel) {
        this.login = login;
        this.papel = papel;

    }

    public DtoUsuarioResponse(Usuario usuario) {
        this.login = usuario.getLogin();
        this.papel = usuario.getPapel().getNome();
        this.usuarioId = usuario.getId().toString();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }
}
