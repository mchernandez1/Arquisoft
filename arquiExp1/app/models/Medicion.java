package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by d.marino10 on 15/02/2017.
 */
@Entity
@Table (name = "mediciones")
public class Medicion extends Model
{
    public static Model.Finder<Long, Medicion> FINDER = new Finder<>(Medicion.class);

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MedicionEntity")

    private Long referencia;

    private String estado;

    private String frecuencia;

    private String estres;

    private String presion;

    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy.MM.dd 'at' HH:mm")
    private Calendar fecha;

    @ManyToOne
    private Paciente paciente;

    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------

    public Medicion(){

    }

    public Medicion(String frecuencia, String estres, String presion) {
        this.frecuencia = frecuencia;
        this.estres = estres;
        this.presion = presion;
        this.paciente = paciente;
        this.fecha =Calendar.getInstance();
    }

    public Medicion(String estado, String frecuencia, String estres, String presion, Paciente paciente) {
        this.estado = estado;
        this.frecuencia = frecuencia;
        this.estres = estres;
        this.presion = presion;
        this.paciente = paciente;
        this.fecha =Calendar.getInstance();
    }

    //-----------------------------------------------------------
    // Getters y setters
    //-----------------------------------------------------------

    @PrePersist
    private void creationTimestamp() {
        this.fecha =Calendar.getInstance();
    }

    public Long getReferencia() {
        return referencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getEstres() {
        return estres;
    }

    public void setEstres(String estres) {
        this.estres = estres;
    }

    public String getPresion() {
        return presion;
    }

    public void setPresion(String presion) {
        this.presion = presion;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Calendar getFecha() {return fecha;}

}
