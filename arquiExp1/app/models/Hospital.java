package models;

import com.avaje.ebean.Model;
import sun.management.Sensor;

import java.util.List;
import javax.persistence.*;

/**
 * Created by af.moreno10 on 10/02/2017.
 */
@Entity
@Table(name="hospital")
public class Hospital extends Model {

    public static Finder<Long, Hospital> FINDER = new Finder<>(Hospital.class);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "HospitalEntity")
    private Long id;

    private List<Paciente> listaPacientes;
    private List<Medico> listaMedicos;
    private List<Sensor> listaSensores;

    private int numeroHabitaciones;


    public Hospital(){
        this.id = null;
        this.listaPacientes = null;
        this.listaMedicos = null;
        this.listaSensores = null;
    }

    public Hospital(Long id){
        this.id = id;
    }

    public Hospital(Long id, List<Paciente> listaPacientes, List<Medico> listaMedicos, List<Sensor> listaSensores, int numeroHabitaciones){
        this.id = id;
        this.listaPacientes = listaPacientes;
        this.listaMedicos = listaMedicos;
        this.listaSensores = listaSensores;
        this.numeroHabitaciones = numeroHabitaciones;
    }

    public List<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public void setListaPacientes(List<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    public List<Medico> getListaMedicos() {
        return listaMedicos;
    }

    public void setListaMedicos(List<Medico> listaMedicos) {
        this.listaMedicos = listaMedicos;
    }

    public List<Sensor> getListaSensores() {
        return listaSensores;
    }

    public void setListaSensores(List<Sensor> listaSensores) {
        this.listaSensores = listaSensores;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "Hospital{" +
                "id=" + id +
                ", listaPacientes='" + listaPacientes.toString() +
                ", listaMedicos='" + listaMedicos.toString() +
                ", listaSensores='" + listaSensores.toString() +
                ", numeroHabitaciones='" + numeroHabitaciones +
                '}';
    }
}
