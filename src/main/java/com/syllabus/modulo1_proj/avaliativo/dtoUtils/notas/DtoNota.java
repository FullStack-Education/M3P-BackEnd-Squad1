package com.syllabus.modulo1_proj.avaliativo.dtoUtils.notas;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class DtoNota {
    @NotNull
    private Double valor;
    private LocalDate dataNota = LocalDate.now();

    @NotNull
    @Min(value = 0, message = "Código do Aluno inválido.")
    private Long aluno_id;

    @NotNull
    @Min(value = 0, message = "Código do Docente inválido.")
    private Long docente_id;

    @NotNull
    @Min(value = 0, message = "Código da Materia inválido.")
    private Long materia_id;

    private Long turma_id;
    private LocalDate dataTermino;
    private String nomeAvaliacao;
    private String horario;

    public DtoNota(){}
    public DtoNota(Double valor, LocalDate dataNota, Long aluno_id, Long docente_id, Long materia_id) {
        this.valor = valor;
        this.dataNota = dataNota;
        this.aluno_id = aluno_id;
        this.docente_id = docente_id;
        this.materia_id = materia_id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDataNota() {
        return dataNota;
    }

    public void setDataNota(LocalDate dataNota) {
        this.dataNota = dataNota;
    }

    public Long getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(Long aluno_id) {
        this.aluno_id = aluno_id;
    }

    public Long getDocente_id() {
        return docente_id;
    }

    public void setDocente_id(Long docente_id) {
        this.docente_id = docente_id;
    }

    public Long getMateria_id() {
        return materia_id;
    }

    public void setMateria_id(Long materia_id) {
        this.materia_id = materia_id;
    }

    public Long getTurma_id() {
        return turma_id;
    }

    public void setTurma_id(Long turma_id) {
        this.turma_id = turma_id;
    }

    public String getNomeAvaliacao() {
        return nomeAvaliacao;
    }

    public void setNomeAvaliacao(String nomeAvaliacao) {
        this.nomeAvaliacao = nomeAvaliacao;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
