package edu.ufsj.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Usuario {
    private Integer id;
    private String login;
    private String password;
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private LocalDateTime cadastrado;
    private LocalDateTime editado;
    private TipoUsuario tipoUsuario;

    public Usuario(String login, String password,
                   String cpf, String nome, String telefone, String email,
                   LocalDateTime editado,
                   TipoUsuario tipoUsuario) {
        this.login = login;
        this.password = password;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.editado = editado;
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.id, other.id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
