package models;

import com.avaje.ebean.Finder;
import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Created by af.moreno10 on 10/02/2017.
 */
@Entity

@Table(name="urgencia")
public class Urgencia extends Model {

    public static Finder<Long, Urgencia> FINDER = new Finder<>(Urgencia.class);





    @Id
    private Long id;
    private Long latitud;
    private Long longitud;
    private Paciente paciente;



    public Urgencia(){
        this.id = null;
        this.latitud = null;
        this.longitud = null;
        this.paciente = null;
    }

    public Urgencia(Long id){
        this.id = id;
    }

    public Urgencia(Long id, Long latitud, Long longitud, Paciente paciente){
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.paciente = paciente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Long getLatitud() {
        return latitud;
    }

    public void setLatitud(Long latitud) {
        this.latitud = latitud;
    }

    public Long getLongitud() {
        return longitud;
    }

    public void setLongitud(Long longitud) {
        this.longitud = longitud;
    }


    @Override
    public String toString(){
        return "Urgencia{" +
                "id=" + id+
                ", latitud='" + latitud +
                ", longitud='" + longitud +
                ", paciente='" + paciente.toString() +
                '}';
    }

    public static Urgencia bind(JsonNode j){
        Long i = j.findPath("id").asLong();
        Urgencia urgencia = new Urgencia(i);
        return urgencia;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
