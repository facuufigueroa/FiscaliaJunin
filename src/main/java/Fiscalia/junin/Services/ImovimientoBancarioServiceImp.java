package Fiscalia.junin.Services;

import Fiscalia.junin.Model.Causa;
import Fiscalia.junin.Model.LlamadaTelefonica;
import Fiscalia.junin.Model.MovimientoBancario;
import Fiscalia.junin.Repository.ImovimientoBancarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImovimientoBancarioServiceImp implements ImovimientoBancarioService{

    @Autowired
    ImovimientoBancarioRepository imovimientoBancarioRepository;

    @Override
    public void save(MovimientoBancario movimientoBancario) {
        imovimientoBancarioRepository.save(movimientoBancario);
    }

    @Override
    public MovimientoBancario findById(Long id) {
        return imovimientoBancarioRepository.findById(id).orElse(null);
    }

}
