package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by AndresFelipe on 15/02/2017.
 */
@Entity
public class Registro extends Model{

    public static Finder<Long, Registro> FINDER = new Finder<>(Registro.class);

    //------------------------------
    //     ATRIBUTOS
    //------------------------------
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "Registro")
    private Long id;

    @ManyToOne
    private Sensor sensor;

    @Temporal(TemporalType.DATE)
    private Date creadoA;

    private Double dato;

    //------------------------------
    //     CONSTRUCTOR
    //------------------------------
    public Registro(){
        id=null;
    }

    public Registro(Long pId){
        id = pId;
    }

    public Registro(Long pId, double pDato){
        id = pId;
        dato = pDato;
    }

    //------------------------------
    //     METODOS
    //------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Date getCreadoA() {
        return creadoA;
    }

    public void setCreadoA(Date creadoA) {
        this.creadoA = creadoA;
    }

    public Double getDato() {
        return dato;
    }

    public void setDato(Double dato) {
        this.dato = dato;
    }
}
