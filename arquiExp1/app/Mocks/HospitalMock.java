package Mocks;

import models.Hospital;
import models.Urgencia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan on 2/15/2017.
 */
public class HospitalMock {

    public static List<Hospital> listaH;

    public HospitalMock(){
        listaH = new ArrayList();
    }

    public static List<Hospital> getHospitales(){
        return listaH;
    }

    public static Hospital getHospital(Long id){
        for(int i = 0; i<listaH.size(); i++){
            Hospital actual = listaH.get(i);
            if(actual.getId()==id){
                return actual;
            }
        }
        return null;
    }

    public static boolean delete(Long id){
        Hospital hospital = getHospital(id);
        boolean respuesta = false;
        if(hospital==null){
            listaH.remove(hospital);
            respuesta = true;
        }
        return respuesta;
    }

    public void add(Hospital hospital){
        listaH.add(hospital);
    }

    public void update(Hospital hospital){
        for(int i = 0; i<listaH.size(); i++){
            Hospital actual = listaH.get(i);
            if(actual.getId()==hospital.getId()){
                listaH.set(i, hospital);
            }
        }
    }

}
