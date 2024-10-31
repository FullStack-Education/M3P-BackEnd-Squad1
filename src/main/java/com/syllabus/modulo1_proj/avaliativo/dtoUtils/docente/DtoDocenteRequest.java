package com.syllabus.modulo1_proj.avaliativo.dtoUtils.docente;
import com.syllabus.modulo1_proj.avaliativo.entities.Turma;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;

@AllArgsConstructor
public class DtoDocenteRequest {
    @NotBlank
    private String nome;
    private Long id;

    private String genero;
    private LocalDate nascimento;
    private String cpf;
    private String rg;
    private String estadoCivil;
    private String telefone;
    private String email;
    private String naturalidade;
    private String cep;
    private String numero;
    private Turma turma;
    private String complemento;
    private String referencia;
    private ArrayList<String> materias;

    @NotNull
    @Min(value = 0, message = "Código de Usuário inválido.")
    private Long usuario_id;


    public DtoDocenteRequest() {}
    public DtoDocenteRequest(String nome, Long usuario_id, String nome_papel) {
        this.nome = nome;
        this.usuario_id = usuario_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }
}
