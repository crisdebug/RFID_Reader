package com.example.samuel.rfidreader.database;

public class Funcionario {
    private long id;
    private String nome;
    private String code;
    private boolean entrou = false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isEntrou() {
        return entrou;
    }

    public void setEntrou(boolean entrou) {
        this.entrou = entrou;
    }
}
