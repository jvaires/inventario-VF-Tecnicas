package br.edu.uni7.tecnicas.entities;

public class Usuario {

    private String nome;

    public Usuario(String nome)
    {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
