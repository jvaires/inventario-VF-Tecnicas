package br.edu.uni7.tecnicas.controller;

import java.util.Date;

public class Commit {
    private String mensagem;
    private Date data;
    private int codigo;
    private String autor;

    public Commit(String mensagem, String autor, int codigo){
        this.mensagem = mensagem;
        this.data = new Date();
        this.autor = autor;
        this.codigo = codigo;
    }
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    @Override
    public String toString() {
        return "Commit{" +
                "nome='" + mensagem + '\'' +
                ", data=" + data +
                ", codigo=" + codigo +
                '}';
    }

}
