package Mocks;

import models.Paciente;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by d.marino10 on 15/02/2017.
 */
public class PacienteMock {

    //atributos
    public static List<Paciente> lista;

    //constructor

    public PacienteMock()
    {
        lista = new ArrayList<>();
    }

    //

    public static List<Paciente> getAll() {
        return lista;
    }

    public static Paciente get(Long id){
        for (int i = 0; i<lista.size();i++){
            if(lista.get(i).getDocumento()==id){
                return lista.get(i);
            }
        }
        return null;
    }

    public static boolean delete(Long id){
        Paciente p = get(id);
        boolean res=false;
        if(p==null) {
            lista.remove(p);
            res=true;
        }
        return res;
    }

    public void save(Paciente paciente){
        lista.add(paciente);
    }

    public void update(Paciente paciente){
        for (int i = 0; i<lista.size();i++){
            if(lista.get(i).getDocumento()==paciente.getDocumento()){
                lista.set(i,paciente);
            }
        }
    }
}
