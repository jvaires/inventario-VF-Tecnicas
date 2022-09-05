package br.edu.uni7.tecnicas.controller;

import java.time.LocalDateTime;
import java.util.Date;

public class Commit {
    private String mensagem;
    private Date data;
    private int codigo;
    private String autor;

    public Commit(String mensagem, int codigo){
        this.mensagem = mensagem;
        this.data = new Date();
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
    @Override
    public String toString() {
        return "Commit{" +
                "nome='" + mensagem + '\'' +
                ", data=" + data +
                ", codigo=" + codigo +
                '}';
    }
}
