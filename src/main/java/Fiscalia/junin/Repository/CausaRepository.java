package Fiscalia.junin.Repository;

import Fiscalia.junin.Model.Causa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CausaRepository extends JpaRepository<Causa,Long> {


    @Query("SELECT c FROM Causa c ORDER BY c.id DESC")
    public List<Causa> findAllByDesc();

    /* Buscar causas por victima*/
    public List<Causa> findByVictima(String victima);

    /*Buscar causas por victimario*/
    public List<Causa> findByVictimario(String victimario);

    /*Buscar causas por numero de expediente*/
    public List<Causa> findByNumExpediente(String numExpediente);

}
