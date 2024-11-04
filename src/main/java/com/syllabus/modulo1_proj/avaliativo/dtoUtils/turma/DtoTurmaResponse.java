package com.syllabus.modulo1_proj.avaliativo.dtoUtils.turma;
import com.syllabus.modulo1_proj.avaliativo.entities.Turma;

import java.time.LocalDate;

public class DtoTurmaResponse {

    private String nomeTurma;

    private String nomeDocente;

    private String nomeCurso;

    private Long id;

    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private String nomeMateria;

    public DtoTurmaResponse(){}
    public DtoTurmaResponse(String nomeTurma, String nomeDocente, String nomeCurso, Long id) {
        this.nomeTurma = nomeTurma;
        this.nomeDocente = nomeDocente;
        this.nomeCurso = nomeCurso;
        this.id = id;
    }

    public DtoTurmaResponse(Turma turma) {
        this.nomeTurma = turma.getNome();
        this.nomeDocente = turma.getDocente().getNome();
        this.nomeCurso = turma.getCurso().getNome();
        this.id = turma.getId();
        this.dataInicio = turma.getDataInicio();
        this.dataTermino = turma.getDataTermino();
        this.nomeMateria = turma.getMateria().getNome();

    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public String getNomeDocente() {
        return nomeDocente;
    }

    public void setNomeDocente(String nomeDocente) {
        this.nomeDocente = nomeDocente;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getNomeMateria() {
        return nomeMateria;
    }

    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria = nomeMateria;
    }
}
