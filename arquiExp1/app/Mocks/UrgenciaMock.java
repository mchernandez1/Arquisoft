package Mocks;

import models.Urgencia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan on 2/15/2017.
 */
public class UrgenciaMock {

    public static List<Urgencia> listaU;

    public UrgenciaMock(){
        listaU = new ArrayList();
    }

    public static List<Urgencia> getUrgencias(){
        return listaU;
    }

    public static Urgencia getUrgencia(Long id){
        for(int i = 0; i<listaU.size(); i++){
            Urgencia actual = listaU.get(i);
            if(actual.getId()==id){
                return actual;
            }
        }
        return null;
    }

    public static boolean delete(Long id){
        Urgencia urgencia = getUrgencia(id);
        boolean respuesta = false;
        if(urgencia==null){
            listaU.remove(urgencia);
            respuesta = true;
        }
        return respuesta;
    }

    public void add(Urgencia urgencia){
        listaU.add(urgencia);
    }

    public void update(Urgencia urgencia){
        for(int i = 0; i<listaU.size(); i++){
            Urgencia actual = listaU.get(i);
            if(actual.getId()==urgencia.getId()){
                listaU.set(i, urgencia);
            }
        }
    }

}
