package Fiscalia.junin.Services;

import Fiscalia.junin.Model.Causa;
import Fiscalia.junin.Model.LlamadaTelefonica;

public interface IllamadaTelefonicaService {

    public void save(LlamadaTelefonica llamadaTelefonica);
    public LlamadaTelefonica findById(Long id);
    public void delete(Long id);
}
