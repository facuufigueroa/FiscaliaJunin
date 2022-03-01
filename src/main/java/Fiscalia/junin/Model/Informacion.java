package Fiscalia.junin.Model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Informacion {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long id;

    @NotNull
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public Date fecha;



    @ManyToOne()
    @JoinColumn(name = "causa_id")
    private Causa causa;

    @NotNull
    private String descripcion;



    private Boolean esLlamada;
    private Boolean esMovimiento;
    private Boolean esRedSocial;


    public Informacion() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Informacion(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Causa getCausa() {
        return causa;
    }

    public void setCausa(Causa causa) {
        this.causa = causa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEsLlamada() {
        return esLlamada;
    }

    public void setEsLlamada(Boolean esLlamada) {
        this.esLlamada = esLlamada;
    }

    public Boolean getEsMovimiento() {
        return esMovimiento;
    }

    public void setEsMovimiento(Boolean esMovimiento) {
        this.esMovimiento = esMovimiento;
    }

    public Boolean getEsRedSocial() {
        return esRedSocial;
    }

    public void setEsRedSocial(Boolean esRedSocial) {
        this.esRedSocial = esRedSocial;
    }
}

