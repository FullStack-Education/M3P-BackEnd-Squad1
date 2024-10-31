package com.syllabus.modulo1_proj.avaliativo.dtoUtils.notas;
import com.syllabus.modulo1_proj.avaliativo.entities.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class DtoNotaResponse {

    private Long id;
    private Double valor;
    private LocalDate dataNota;
    private Aluno aluno;
    private Docente docente;
    private Materia materia;
    private Turma turma;
    private LocalDate dataTermino;
    private String nomeAvaliacao;
    private String horario;

    public DtoNotaResponse(){}
    public DtoNotaResponse(Double valor, LocalDate data, Long id) {
        this.valor = valor;
        this.dataNota = data;
        this.id = id;
    }

    public DtoNotaResponse(Nota nota) {
        this.valor = nota.getValor();
        this.dataNota = nota.getDataNota();
        this.id = nota.getId();
        this.aluno = nota.getAluno();
        this.docente = nota.getDocente();
        this.materia = nota.getMateria();
        this.turma = nota.getTurma();
        this.dataTermino = nota.getDataTermino();
        this.nomeAvaliacao = nota.getNomeAvaliacao();
        this.horario = nota.getHorario();

    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return dataNota;
    }

    public void setData(LocalDate data) {
        this.dataNota = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataNota() {
        return dataNota;
    }

    public void setDataNota(LocalDate dataNota) {
        this.dataNota = dataNota;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getNomeAvaliacao() {
        return nomeAvaliacao;
    }

    public void setNomeAvaliacao(String nomeAvaliacao) {
        this.nomeAvaliacao = nomeAvaliacao;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
