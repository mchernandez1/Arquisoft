package models;

import java.util.List;

/**
 * Created by af.moreno10 on 10/02/2017.
 */
public class Hospital {

    private List<Paciente> listaPacientes;
    private List<Medico> listaMedicos;
    private int numeroCamas;


    public Hospital(List<Paciente> listaPacientes, List<Medico> listaMedicos, int numeroCamas){
        this.listaPacientes = listaPacientes;
        this.listaMedicos = listaMedicos;
        this.numeroCamas = numeroCamas;
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
}
