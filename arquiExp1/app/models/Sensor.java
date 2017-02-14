package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by AndresFelipe on 14/02/2017.
 */
@Entity
public class Sensor extends Model {

    //----- CONSTANTES -----
    /**
     * Constantes creadas para diferenciar los tipos de sensores.
     */
    private static int FRECUENCIA = 1;
    private static int PRESION = 2;
    private static int ESTRES = 3;

    public static Finder<Long, Sensor> finder = new Finder<>(Sensor.class);

    //---- ATRIBUTOS ----
    /**
     * Atributo identificador del sensor.
     */
    @Id
    private Long id;

    /**
     * Atributo que indica el tipo de sensor.
     */
    private int tipo;

    /**
     * Atributo que contendra el dato recibido por el sensor
     */
    private double dato;


    //---- CONSTRUCTOR ----
    /**
     * Metodo constructor de la clase Sensor
     */
    public Sensor(Long pId, int pTipo){
        id = pId;
        tipo = pTipo;
        dato = 0.0;
    }

    //---- METODOS ----

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
    public int getTipo() {
        return tipo;
    }

    /**
     * Metodo que modifica el tipo del sensor
     * @param tipo nuevo tipo del sensor
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * Metodo que retorna el dato obtenido por el sensor
     * @return dato obtenido por el sensor
     */
    public double getDato() {
        return dato;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", dato=" + dato +
                '}';
    }
}
