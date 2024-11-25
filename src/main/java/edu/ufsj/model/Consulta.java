package edu.ufsj.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Consulta {

    private Integer id;
    private Paciente paciente;
    private Medico medico;
    private String descricao;
    private LocalDateTime cadastrado;
    private LocalDateTime dataAgendamento;
    private LocalDateTime dataFim;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consulta consulta = (Consulta) o;
        return Objects.equals(id, consulta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


    public boolean isConsultaRealizada() {
        return getDataFim() != null;
    }

    public boolean isValidForRegisterBD() {
        return getMedico() != null || getPaciente() != null || getDataAgendamento() != null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getCadastrado() {
        return cadastrado;
    }

    public void setCadastrado(LocalDateTime cadastrado) {
        this.cadastrado = cadastrado;
    }

    public LocalDateTime getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDateTime dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }
    
    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

}
