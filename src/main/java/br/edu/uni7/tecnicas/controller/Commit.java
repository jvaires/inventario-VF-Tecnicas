package br.edu.uni7.tecnicas.controller;

import java.time.LocalDateTime;
import java.util.Date;

public class Commit {
    private String nome;
    private Date data;
    private int codigo;

    @Override
    public String toString() {
        return "Commit{" +
                "nome='" + nome + '\'' +
                ", data=" + data +
                ", codigo=" + codigo +
                '}';
    }

    public Commit(String nome, int codigo){
        this.nome = nome;
        this.data = new Date();
        this.codigo = codigo;
    }

}
