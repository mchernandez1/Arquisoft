package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;

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

    public static Finder<Long, Sensor> FINDER = new Finder<>(Sensor.class);

    //-----------------------------
    // ATRIBUTOS
    //-----------------------------
    /**
     * Atributo identificador del sensor.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "SensorEntity")
    private Long id;

    @ManyToOne
    private Paciente paciente;

    @OneToMany
    private ArrayList<Registro> registros;
    /**
     * Atributo que indica el tipo de sensor.
     */
    private TipoSensor tipo;

    private String fechaAsignacion;

    //-------------------------
    // CONSTRUCTOR
    //-------------------------
    /**
     * Metodo constructor sin atributos
     */
    public Sensor(){

    }

    /**
     * Metodo constructor de la clase Sensor
     */
    public Sensor(Long pId, TipoSensor pTipo, String pFecha){
        id = pId;
        tipo = pTipo;
        fechaAsignacion = pFecha;
    }

    public Sensor (Long pId, TipoSensor pTipo, String pFecha, Paciente pPaciente){
        id = pId;
        tipo = pTipo;
        fechaAsignacion = pFecha;
        paciente = pPaciente;
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
     * Metodo que retorna el paciente asociado al sensor
     * @return paciente
     */
    public Paciente getPaciente(){ return paciente;}

    /**
     * Metodo que modifica el paciente asociado al sensor
     * @param pPaciente nuevo paciente
     */
    public void setPaciente( Paciente pPaciente){ paciente = pPaciente;}

    public String getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsigancion(String fecha) {
        this.fechaAsignacion = fecha;
    }

    public ArrayList<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(ArrayList<Registro> registros) {
        this.registros = registros;
    }

    //------------------------------
    // METODOS AUXILIARES
    //------------------------------
}
