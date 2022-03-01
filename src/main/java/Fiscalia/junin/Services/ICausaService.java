package Fiscalia.junin.Services;

import Fiscalia.junin.Model.Causa;

import java.util.List;


public interface ICausaService{

    public List<Causa> findAllByDesc();
    public Causa save(Causa c);
    public Causa findById(Long id);
    public void delete(Long id);
    public List<Causa> findByVictima(String victima);
    public List<Causa> findByVictimario(String victimario);
    public List<Causa> findByNumExpediente(String numExpediente);


}
