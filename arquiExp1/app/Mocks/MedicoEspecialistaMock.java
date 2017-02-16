package Mocks;

import models.MedicoEspecialista;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maria on 15/02/2017.
 */
public class MedicoEspecialistaMock
{
    //atributos
    public static List<MedicoEspecialista> lista;

    //constructor

    public MedicoEspecialistaMock()
    {
        lista = new ArrayList<>();
    }

    //

    public static List<MedicoEspecialista>getAll() {
        return lista;
    }

    public static MedicoEspecialista get(Long id){
        for (int i = 0; i<lista.size();i++){
            if(lista.get(i).getId()==id){
                return lista.get(i);
            }
        }
        return null;
    }

    public static boolean delete(Long id){
        MedicoEspecialista p = get(id);
        boolean res=false;
        if(p==null) {
            lista.remove(p);
            res=true;
        }
        return res;
    }

    public void save(MedicoEspecialista medico){
        lista.add(medico);
    }

    public void update(MedicoEspecialista medico){
        for (int i = 0; i<lista.size();i++){
            if(lista.get(i).getId()==medico.getId()){
                lista.set(i,medico);
            }
        }
    }
}
