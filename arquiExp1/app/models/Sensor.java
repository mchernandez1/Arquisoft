package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by AndresFelipe on 14/02/2017.
 */
@Entity
@Table(name = "sensores")
public class Sensor extends Model {

    //-----------------------------
    // CONSTANTES
    //-----------------------------
    /**
     * Constantes creadas para diferenciar los tipos de sensores.
     */
    public enum TipoSensor{
        FRECUENCIA,
        PRESION,
        ESTRES
    }

    public static Finder<Long, Sensor> finder = new Finder<>(Sensor.class);

    //-----------------------------
    // ATRIBUTOS
    //-----------------------------
    /**
     * Atributo identificador del sensor.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "Sensor")
    private Long id;

    /**
     * Atributo que indica el tipo de sensor.
     */
    private TipoSensor tipo;

    /**
     * Atributo que contendra el dato recibido por el sensor
     */
    private double dato;

    /**
     * Atributo que indica la fecha de la toma del dato del sensor
     */
    private Date fecha;

    //-------------------------
    // CONSTRUCTOR
    //-------------------------

    /**
     * Metodo constructor sin atributos
     */
    public Sensor (){

    }

    /**
     * Metodo constructor de la clase Sensor
     */
    public Sensor(TipoSensor pTipo){
        tipo = pTipo;
        dato = 0.0;
        fecha = new Date();
    }

    //--------------------------
    // METODOS
    //--------------------------
    /**
     * Metodo que retorna el identificador del senor
     * @return id del sensor
     */
    public Long getId() {
        return id;
    }

    /**
     * Metodo que modifica el id del sensor
     * @param id nuevo id del sensor
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Metodo que retorna el tipo del sensor
     * @return tipo del sensor
     */
    public TipoSensor getTipo() {
        return tipo;
    }

    /**
     * Metodo que modifica el tipo del sensor
     * @param tipo nuevo tipo del sensor
     */
    public void setTipo(TipoSensor tipo) {
        this.tipo = tipo;
    }

    /**
     * Metodo que retorna el dato obtenido por el sensor
     * @return dato obtenido por el sensor
     */
    public double getDato() {
        return dato;
    }

    /**
     * Metodo que modifica el dato del sensor
     * @param dato nuevo dato del sensor
     */
    public void setDato(double dato){this.dato = dato;}

    /**
     * Metodo que retorna la fecha en la cual se tomo la medicion del dato.
     * @return fecha de la medicion
     */
    public Date getFecha() {return fecha;}

    /**
     * Metodo que modifica la fecha en la que se tomo la medicion
     * @param fecha nueva fecha de medicion
     */
    public void setFecha(Date fecha) {this.fecha = fecha;}

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", dato=" + dato +
                ", fecha=" + fecha +
                '}';
    }

    //------------------------------
    // METODOS AUXILIARES
    //------------------------------
}
