package Fiscalia.junin.Services;


import Fiscalia.junin.Model.Causa;
import Fiscalia.junin.Repository.CausaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICausaServiceImpl implements ICausaService{

    @Autowired
    private CausaRepository causaRepository;

    @Override
    public List<Causa> findAllByDesc() {
        return causaRepository.findAllByDesc();
    }

    @Override
    public Causa save(Causa c) {
        return causaRepository.save(c);
    }

    @Override
    public Causa findById(Long id) {
        return causaRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        causaRepository.deleteById(id);
    }

    @Override
    public List<Causa> findByVictima(String victima) {
        return causaRepository.findByVictima(victima);
    }

    @Override
    public List<Causa> findByVictimario(String victimario) {
        return causaRepository.findByVictimario(victimario);
    }

    @Override
    public List<Causa> findByNumExpediente(String numExpediente) {
        return causaRepository.findByNumExpediente(numExpediente);
    }


}
