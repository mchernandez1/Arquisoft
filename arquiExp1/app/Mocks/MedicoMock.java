package Mocks;

import models.Medico;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maria on 15/02/2017.
 */
public class MedicoMock
{
    //atributos
    public static List<Medico> lista;

    //constructor

    public MedicoMock()
    {
        lista = new ArrayList<>();
    }

    //

    public static List<Medico>getAll() {
        return lista;
    }

    public static Medico get(Long id){
        for (int i = 0; i<lista.size();i++){
            if(lista.get(i).getIdMedico()==id){
                return lista.get(i);
            }
        }
        return null;
    }

    public static boolean delete(Long id){
        Medico p = get(id);
        boolean res=false;
        if(p==null) {
            lista.remove(p);
            res=true;
        }
        return res;
    }

    public void save(Medico medico){
        lista.add(medico);
    }

    public void update(Medico medico){
        for (int i = 0; i<lista.size();i++){
            if(lista.get(i).getIdMedico()==medico.getIdMedico()){
                lista.set(i,medico);
            }
        }
    }
}
