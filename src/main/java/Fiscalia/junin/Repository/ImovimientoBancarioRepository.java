package Fiscalia.junin.Repository;

import Fiscalia.junin.Model.MovimientoBancario;
import Fiscalia.junin.Services.ImovimientoBancarioServiceImp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovimientoBancarioRepository extends JpaRepository <MovimientoBancario,Long>{
}
