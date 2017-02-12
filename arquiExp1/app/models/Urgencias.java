package models;

/**
 * Created by af.moreno10 on 10/02/2017.
 */
public class Urgencias {

    private Long latitud;
    private Long longitud;
    private String estadoPaciente;

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
}
