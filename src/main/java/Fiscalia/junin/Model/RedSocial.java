package Fiscalia.junin.Model;

import javax.persistence.Entity;
import java.util.Date;


@Entity(name = "red_social")
public class RedSocial extends Informacion{


    private String tipo;
    private String perfil;


    public RedSocial(Date fecha, String tipo, String perfil) {
        super(fecha);
        this.tipo = tipo;
        this.perfil = perfil;


    }



    public RedSocial() {
        super();
        this.setEsLlamada(false);
        this.setEsMovimiento(false);
        this.setEsRedSocial(true);

    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

}
