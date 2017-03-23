package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by af.moreno10 on 10/02/2017.
 */

public class MedicoEspecialista extends Medico
{

    private String informeMarcaPasos;

    private String consejos;

    /**
     * Constructor 1
     *
     * @param id           Identificación de medico
     * @param nombre       Nombre del medico
     * @param especialidad Especialidad que posee el medico
     * @param descripcion  Información adicional del medico
     */
    public MedicoEspecialista(long id, String nombre, String especialidad, String descripcion, String informacionMarcaPasos, String consejos) {
        super(id, nombre, especialidad, descripcion);
        informeMarcaPasos = informacionMarcaPasos;
        this.consejos = consejos;
    }


    public String getInformeMarcaPasos() {
        return informeMarcaPasos;
    }

    public void setInformeMarcaPasos(String informeMarcaPasos) {
        this.informeMarcaPasos = informeMarcaPasos;
    }


    public String getConsejos() {
        return consejos;
    }

    public void setConsejos(String consejos) {
        this.consejos = consejos;
    }


}
