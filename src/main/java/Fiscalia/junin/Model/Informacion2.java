package Fiscalia.junin.Model;

import java.util.Date;

public class Informacion2 implements Comparable<Informacion2> {

    private Long id;

    private String tipoInformacion;

    private Date fecha;



    public Informacion2(String tipoInformacion, Date fecha, Long id) {
        this.tipoInformacion = tipoInformacion;
        this.fecha = fecha;
        this.id=id;
    }

    public String getTipoInformacion() {
        return tipoInformacion;
    }

    public void setTipoInformacion(String tipoInformacion) {
        this.tipoInformacion = tipoInformacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int compareTo(Informacion2 o) {
        if (getFecha() == null || o.getFecha() == null) {
            return 0;
        }
        return getFecha().compareTo(o.getFecha());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

