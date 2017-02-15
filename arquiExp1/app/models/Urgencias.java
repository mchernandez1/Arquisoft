package models;

import com.sun.javafx.beans.IDProperty;

/**
 * Created by af.moreno10 on 10/02/2017.
 */
@Entity

@Table(name="urgencias")
public class Urgencias extends model{

    public static Finder<Long, Urgencias> FINDER = new Finder<>(Urgencias.class);

    @Id

    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Urgencia")

    private Long id;
    private Long latitud;
    private Long longitud;
    private String estadoPaciente;


    public Urgencias(){
        this.id = null;
        this.latitud = null;
        this.longitud = null;
        this.estadoPaciente = "";
    }

    public Urgencias(Long id){
        this.id = id;
    }

    public Urgencias(Long latitud, Long longitud, String estadoPaciente){
        this.latitud = latitud;
        this.longitud = longitud;
        this.estadoPaciente = estadoPaciente;
    }

    public Long getLatitud() {
        return latitud;
    }

    public void setLatitud(Long latitud) {
        this.latitud = latitud;
    }

    public Long getLongitud() {
        return longitud;
    }

    public void setLongitud(Long longitud) {
        this.longitud = longitud;
    }

    public String getEstadoPaciente() {
        return estadoPaciente;
    }

    public void setEstadoPaciente(String estadoPaciente) {
        this.estadoPaciente = estadoPaciente;
    }


    @Override
    public String toString(){
        return "Urgencia{" +
                "id=" + id+
                ", latitud='" + latitud +
                ", longitud='" + longitud +
                ", estadoPaciente='" + estadoPaciente +
                '}';
    }

}
