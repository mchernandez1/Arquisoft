package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Clase que representa la información de un medico
 * Created by af.moreno10 on 10/02/2017.
 */

@Entity

public class Medico extends Model
{
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Referencia que identifica al medico en el sistema.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Medico")

    /**
     * Identificación dentro del sistema del medico.
     */
    private int idMedico;

    /**
     * Nombre del medico.
     */
    private String nombreMedico;

    /**
     * Especialidad medico.
     */
    private String especialidadMedico;

    /**
     * Descripción general del medico.
     */
    private String descripcionMedico;

    //-------------------------------------------------------------
    //Constructores
    //-------------------------------------------------------------

    /**
     * Constructor 1
     * @param id Identificación de medico
     * @param nombre Nombre del medico
     * @param especialidad Especialidad que posee el medico
     * @param descripcion Información adicional del medico
     */
    public Medico(int id, String nombre, String especialidad, String descripcion)
    {
        this.idMedico = id;
        this.nombreMedico = nombre;
        this.especialidadMedico  = especialidad;
        this.descripcionMedico = descripcion;
    }

    /**
     * Constructor 2
     * @param idMedico Identificación del medico
     */

    public Medico(int idMedico)
    {
        this.idMedico = idMedico;
    }

    /**
     * Constructor pruebas SIN parametros
     */
    public Medico()
    {
        this.idMedico=0;
        this.nombreMedico = "Medico no real";
        this.especialidadMedico = "No se ha graduado";
        this.descripcionMedico = "Grupo sanguíneo: o+. No enfermedades hereditarias";
    }

    //-------------------------------------------------------------
    //Metódos
    //-------------------------------------------------------------


    public int getIdMedico()
    {
        return idMedico;
    }

    public void setIdMedico(int idMedico)
    {
        this.idMedico = idMedico;
    }

    public String getNombreMedico()
    {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico)
    {
        this.nombreMedico = nombreMedico;
    }

    public String getEspecialidadMedico()
    {
        return especialidadMedico;
    }

    public void setEspecialidadMedico(String especialidadMedico)
    {
        this.especialidadMedico = especialidadMedico;
    }

    public String getDescripcionMedico()
    {
        return descripcionMedico;
    }

    public void setDescripcionMedico(String descripcionMedico)
    {
        this.descripcionMedico = descripcionMedico;
    }

    //public List<Medico> getTodosLosMedicos()
    //{
        //List medicos = ;
        //return List
    //}

    @Override
    public String toString()
    {
        return "Medico{" +
                "id Medico:" + idMedico +
                ", Nombre Medico:" + nombreMedico +
                ", Especialidad Medico:" + especialidadMedico +
                ", Descripción Médico:" + descripcionMedico +
                '}';

    }

    /**
     * Crear un objeto Medico apartir de un nodo Json
     */

    public static Medico bind(JsonNode j)
    {
       int idMedico = j.findPath("idMedico").asInt();
        String nombreMedico = j.findPath("Nombre Medico").asText();
        String especialidadMedico = j.findPath("Especialidad medico").asText();
        String descripcionMedico = j.findPath("Descripción medico").asText();
        Medico medico = new Medico(idMedico, nombreMedico, especialidadMedico, descripcionMedico);

        return medico;
    }
}
