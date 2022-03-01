package Fiscalia.junin.Services;

import Fiscalia.junin.Model.Causa;
import Fiscalia.junin.Model.MovimientoBancario;

public interface ImovimientoBancarioService {

    public void save(MovimientoBancario movimientoBancario);
    public MovimientoBancario findById(Long id);
}
