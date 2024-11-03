package com.syllabus.modulo1_proj.avaliativo.entities;

import jakarta.persistence.*;

@Entity
public class Estatisticas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private int alunos;
    private int docentes;
    private int turmas;

    public Estatisticas() {}
    public Estatisticas(int alunos, int docentes, int turmas) {
        this.alunos = alunos;
        this.docentes = docentes;
        this.turmas = turmas;
    }

    public int getAlunos() {
        return alunos;
    }

    public void setAlunos(int alunos) {
        this.alunos = alunos;
    }

    public int getDocentes() {
        return docentes;
    }

    public void setDocentes(int docentes) {
        this.docentes = docentes;
    }

    public int getTurmas() {
        return turmas;
    }

    public void setTurmas(int turmas) {
        this.turmas = turmas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
