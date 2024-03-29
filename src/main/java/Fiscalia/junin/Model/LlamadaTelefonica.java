package Fiscalia.junin.Model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity(name = "llamada_telefonica")
public class LlamadaTelefonica extends Informacion{


    @NotEmpty
    private String numeroReceptor;
    @NotEmpty
    private String numeroEmisor;
    @NotNull
    private Integer duracion;




    public LlamadaTelefonica(){
        this.setEsLlamada(true);
        this.setEsMovimiento(false);
        this.setEsRedSocial(false);
    }

    public String getNumeroReceptor() {
        return numeroReceptor;
    }

    public void setNumeroReceptor(String numeroReceptor) {
        this.numeroReceptor = numeroReceptor;
    }

    public String getNumeroEmisor() {
        return numeroEmisor;
    }

    public void setNumeroEmisor(String numeroEmisor) {
        this.numeroEmisor = numeroEmisor;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
}
