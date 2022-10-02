package Fiscalia.junin.Services;

import Fiscalia.junin.Model.Causa;
import Fiscalia.junin.Model.LlamadaTelefonica;
import Fiscalia.junin.Repository.llamadaTelefonicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IllamadaTelefonicaServiceImp implements IllamadaTelefonicaService {

    @Autowired
    private llamadaTelefonicaRepository llamadaTelefonicaRepository;

    @Override
    public void save(LlamadaTelefonica llamadaTelefonica) {
        llamadaTelefonicaRepository.save(llamadaTelefonica);
    }

    @Override
    public LlamadaTelefonica findById(Long id) {
        return llamadaTelefonicaRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        llamadaTelefonicaRepository.deleteById(id);
    }
}


