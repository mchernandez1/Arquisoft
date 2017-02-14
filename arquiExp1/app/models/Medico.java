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
    //@Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    //private Long referencia;

    /**
     * Nombre del medico.
     */
    private String nombreMedico;

    /**
     * Descripción del medico.
     */
    private String descripcionMedico;
}
