package Mocks;

import models.Medicion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by d.marino10 on 15/02/2017.
 */
public class MedidorMock {

    public static List<Medicion> lista;

    //constructor

    public MedidorMock() {
        lista = new ArrayList<>();
    }

    //

    public static List<Medicion> getAll() {
        return lista;
    }

    public static Medicion get(Long id){
        for (int i = 0; i<lista.size();i++){
            if(lista.get(i).getReferencia()==id){
                return lista.get(i);
            }
        }
        return null;
    }

    public static boolean delete(Long id){
        Medicion medicion = get(id);
        boolean res=false;
        if(medicion==null) {
            lista.remove(medicion);
            res=true;
        }
        return res;
    }

    public void save(Medicion medicion){
        lista.add(medicion);
    }

    public void update(Medicion medicion){
        for (int i = 0; i<lista.size();i++){
            if(lista.get(i).getReferencia()==medicion.getReferencia()){
                lista.set(i,medicion);
            }
        }
    }
}
