package br.edu.uni7.tecnicas.entities;

import br.edu.uni7.tecnicas.entities.Usuario;

import javax.persistence.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table
public class Commit{
    private String mensagem;
    private Date data;

    @Id
    private String codigo;

    @OneToOne
    private Usuario autor;

    @Deprecated
    protected Commit() {
    }

    public Commit(String mensagem, Usuario autor, String codigo, Date data){
        this.mensagem = mensagem;
        this.data = data;
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

    public Date getDiaData()
    {
        Calendar cal = Calendar.getInstance();

        cal.setTime(data);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoAbreviado()
    {
        return codigo.substring(0, 7);
    }

    public String getIntervaloCommit()
    {
        String grandeza = "hours";

        Instant inicio = data.toInstant();
        Instant termino = Instant.now();

        Duration intervalo = Duration.between(inicio, termino);

        long intervaloTempo = intervalo.toMinutes();

        if(intervaloTempo < 60) {
            grandeza = "minutes";

            if (intervaloTempo < 2)
                grandeza = "minute";
        }
        else if(intervaloTempo >= 60 && intervaloTempo < 1440)
        {
            if(intervaloTempo < 120)
                grandeza = "hour";

            intervaloTempo = intervalo.toHours();
        }
        else if(intervaloTempo >= 1440)
        {
            grandeza = "days";

            if (intervaloTempo < 2880)
                grandeza = "day";

            intervaloTempo = intervalo.toDays();
        }

        return intervaloTempo + " " + grandeza;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
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
