

package Fiscalia.junin.Model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity(name = "movimiento_bancario")
public class MovimientoBancario extends Informacion{

    @NotEmpty
    private String cuenta;
    @NotEmpty
    private String usuario;
    @NotEmpty
    private String tipoTransaccion;
    @NotNull
    private int monto;
    @NotEmpty
    private String moneda;
    @NotEmpty
    private String datoCajero;

    public MovimientoBancario() {
        super();
        this.setEsLlamada(false);
        this.setEsMovimiento(true);
        this.setEsRedSocial(false);
    }



    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getDatoCajero() {
        return datoCajero;
    }

    public void setDatoCajero(String datoCajero) {
        this.datoCajero = datoCajero;
    }
}
