package com.syllabus.modulo1_proj.avaliativo.dtoUtils.aluno;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.syllabus.modulo1_proj.avaliativo.entities.Aluno;
import com.syllabus.modulo1_proj.avaliativo.entities.Turma;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;

@AllArgsConstructor
public class DtoAlunoResponse {

    private String nome;
    private Long id;

    private LocalDate dataNascimento;
    private String genero;
    private String cpf;
    private String rg;
    private String estadoCivil;
    private String telefone;
    private String email;
    private String naturalidade;
    private String cep;
    private String cidade;
    private String estado;
    private String logradouro;
    private String bairro;
    private String numero;
    private String referencia;
    private String complemento;
    private Turma turma;


    public DtoAlunoResponse(){}
    public DtoAlunoResponse(String nome, Long id) {
        this.nome = nome;
        this.id = id;
    }

    public DtoAlunoResponse(Aluno aluno) {
        this.nome = aluno.getNome();
        this.id = aluno.getId();
        this.cep = aluno.getCep();
        this.cpf = aluno.getCpf();
        this.rg = aluno.getRg();
        this.numero = aluno.getNumero();
        this.email = aluno.getEmail();
        this.genero = aluno.getGenero();
        this.dataNascimento = aluno.getDataNascimento();
        this.estadoCivil = aluno.getEstadoCivil();
        this.naturalidade = aluno.getNaturalidade();
        this.turma = aluno.getTurma();
        this.telefone = aluno.getTelefone();
        this.complemento = aluno.getComplemento();
        this.referencia = aluno.getReferencia();
        this.cidade = aluno.getCidade();
        this.logradouro = aluno.getLogradouro();
        this.estado = aluno.getEstado();
        this.bairro = aluno.getBairro();

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
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
        return dataNascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.dataNascimento = nascimento;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
}
