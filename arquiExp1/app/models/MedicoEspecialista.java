package models;

import com.avaje.ebean.Model;

/**
 * Created by af.moreno10 on 10/02/2017.
 */
public class MedicoEspecialista extends Model
{

    private long idMedico;

    private String nombreMedico;

    private String descripcionMedico;

    private String especialidad;

    private String informeMarcaPasos;

    private String consejos;

    public long getId() {
        return idMedico;
    }

    public void setId(long id) {
        this.idMedico = id;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getDescripcionMedico() {
        return descripcionMedico;
    }

    public void setDescripcionMedico(String descripcionMedico) {
        this.descripcionMedico = descripcionMedico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
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

    public MedicoEspecialista()
    {

    }

    public MedicoEspecialista(long id, String nombre, String especialidad, String descripcion, String consejo, String informeMarcaPasos)
    {
        this.idMedico = id;
        this.nombreMedico = nombre;
        this.especialidad  = especialidad;
        this.descripcionMedico = descripcion;
        this.consejos = consejo;
        this.informeMarcaPasos = informeMarcaPasos;
    }
}
