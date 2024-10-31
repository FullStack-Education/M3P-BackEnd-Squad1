package com.syllabus.modulo1_proj.avaliativo.dtoUtils.docente;
import com.syllabus.modulo1_proj.avaliativo.entities.Aluno;
import com.syllabus.modulo1_proj.avaliativo.entities.Docente;
import com.syllabus.modulo1_proj.avaliativo.entities.Turma;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.ArrayList;

public class DtoDocenteResponse {
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


    public DtoDocenteResponse() {}
    public DtoDocenteResponse(Docente docente) {
        this.nome = docente.getNome();
        this.id = docente.getId();
        this.cep = docente.getCep();
        this.cpf = docente.getCpf();
        this.rg = docente.getRg();
        this.numero = docente.getNumero();
        this.email = docente.getEmail();
        this.genero = docente.getGenero();
        this.nascimento = docente.getNascimento();
        this.estadoCivil = docente.getEstadoCivil();
        this.naturalidade = docente.getNaturalidade();
        this.materias = docente.getMaterias();
        this.telefone = docente.getTelefone();
        this.complemento = docente.getComplemento();
        this.referencia = docente.getReferencia();
    }

    public @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

}
