package models;



import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by af.moreno10 on 10/02/2017.
 */

public class MedicoTratante extends Medico
{

    /**
     * Constructor 1
     *
     * @param id           Identificación de medico
     * @param nombre       Nombre del medico
     * @param especialidad Especialidad que posee el medico
     * @param descripcion  Información adicional del medico
     */
    public MedicoTratante(long id, String nombre, String especialidad, String descripcion) {
        super(id, nombre, especialidad, descripcion);
    }
}
