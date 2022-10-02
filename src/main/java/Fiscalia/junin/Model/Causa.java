package Fiscalia.junin.Model;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="causa")

public class Causa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@Length(max = 41,min = 21,message = "Debe ingresar 40 digitos")*/
    @Pattern(regexp = "^[P]{2}+[-]+[0-9]{2}+[-]+[0-9]{2}+[-]+[0-9]{6}+[-]+[0-9]{2}+[/]+[0]{2}$",message = "El formato es incorrecto ")
    private String numExpediente;
    @NotEmpty
    private String victima;
    @NotEmpty
    private String victimario;
    @NotEmpty
     private String contexto;

    @Column(name="estado")
    private Boolean estado;

    @NotNull
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fecha2;

    public Causa() {
        this.estado=true;
    }

    @OneToMany(mappedBy = "causa",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Informacion> informacion;

    public List<Informacion> getInformacion() {
        return informacion;
    }

    public void setInformacion(List<Informacion> informacion) {
        this.informacion = informacion;
    }


    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(String numExpediente) {
        this.numExpediente = numExpediente;
    }

    public String getVictima() {
        return victima;
    }

    public void setVictima(String victima) {
        this.victima = victima;
    }

    public String getVictimario() {
        return victimario;
    }

    public void setVictimario(String victimario) {
        this.victimario = victimario;
    }


    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public Date getFecha2() {
        return fecha2;
    }

    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }
}
