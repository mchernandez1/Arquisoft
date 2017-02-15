package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by d.marino10 on 15/02/2017.
 */
@Entity
public class Medicion extends Model {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long referencia;

    private String estado;

    private String frecuencia;

    private String estres;

    private String presion;

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
    }

    public Medicion(String estado, String frecuencia, String estres, String presion, Paciente paciente) {
        this.estado = estado;
        this.frecuencia = frecuencia;
        this.estres = estres;
        this.presion = presion;
        this.paciente = paciente;
    }

    //-----------------------------------------------------------
    // Getters y setters
    //-----------------------------------------------------------


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
}
