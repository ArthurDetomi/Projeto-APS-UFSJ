package edu.ufsj.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import edu.ufsj.utils.StringUtil;

public class Paciente {
    private Integer id;
    private String cpf;
    private String nome;
    private String telefone;
    private String cidade;
    private String estado;
    private String numero;
    private LocalDateTime cadastrado;
    private LocalDateTime editado;
    private List<Consulta> consultas;

    public Paciente(String nome, String cpf, String telefone, String estado, String cidade, String numero) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.estado = estado;
        this.cidade = cidade;
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(id, paciente.id);
    }

    public void formatarCampos() {
        this.cpf = StringUtil.keepOnlyNumbers(this.cpf);
        this.telefone = StringUtil.keepOnlyNumbers(this.telefone);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDateTime getCadastrado() {
        return cadastrado;
    }

    public void setCadastrado(LocalDateTime cadastrado) {
        this.cadastrado = cadastrado;
    }

    public LocalDateTime getEditado() {
        return editado;
    }

    public void setEditado(LocalDateTime editado) {
        this.editado = editado;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
}
