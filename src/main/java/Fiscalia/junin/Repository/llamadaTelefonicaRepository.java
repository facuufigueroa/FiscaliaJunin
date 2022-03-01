package Fiscalia.junin.Repository;

import Fiscalia.junin.Model.LlamadaTelefonica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface llamadaTelefonicaRepository extends JpaRepository <LlamadaTelefonica,Long> {
}
