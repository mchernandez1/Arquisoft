package Mocks;

import models.Medicion;
import models.Sensor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndresFelipe on 15/02/2017.
 */
public class SensorMock {

    public static List<Sensor> lista;

    //constructor

    public SensorMock() {
        lista = new ArrayList<>();
    }


    //

    public static List<Sensor> getAll() {
        return lista;
    }

    public static Sensor get(Long id){
        for (int i = 0; i<lista.size();i++){
            if(lista.get(i).getId()==id){
                return lista.get(i);
            }
        }
        return null;
    }

    public static boolean delete(Long id){
        Sensor sensor = get(id);
        boolean res=false;
        if(sensor==null) {
            lista.remove(sensor);
            res=true;
        }
        return res;
    }

    public void save(Sensor sensor){
        lista.add(sensor);
    }

    public void update(Sensor sensor){
        for (int i = 0; i<lista.size();i++){
            if(lista.get(i).getId()==sensor.getId()){
                lista.set(i,sensor);
            }
        }
    }

    public List<Sensor> getByFecha(String pFecha){
        List<Sensor> listaSen = new ArrayList<Sensor>();
        for(int i=0; i<lista.size();i++){
            if(lista.get(i).getFechaAsignacion()==pFecha)
                listaSen.add(lista.get(i));
        }
        return listaSen;
    }
}
