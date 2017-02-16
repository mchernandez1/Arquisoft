package Mocks;

import models.Urgencias;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan on 2/15/2017.
 */
public class UrgenciaMock {

    public static List<Urgencias> listaU;

    public UrgenciaMock(){
        listaU = new ArrayList();
    }

    public static List<Urgencias> getUrgencias(){
        return listaU;
    }

    public static Urgencias getUrgencia(Long id){
        for(int i = 0; i<listaU.size(); i++){
            Urgencias actual = listaU.get(i);
            if(actual.getId()==id){
                return actual;
            }
        }
        return null;
    }

    public static boolean delete(Long id){
        Urgencias urgencia = getUrgencia(id);
        boolean respuesta = false;
        if(urgencia==null){
            listaU.remove(urgencia);
            respuesta = true;
        }
        return respuesta;
    }

    public void add(Urgencias urgencia){
        listaU.add(urgencia);
    }

    public void update(Urgencias urgencia){
        for(int i = 0; i<listaU.size(); i++){
            Urgencias actual = listaU.get(i);
            if(actual.getId()==urgencia.getId()){
                listaU.set(i, urgencia);
            }
        }
    }

}
