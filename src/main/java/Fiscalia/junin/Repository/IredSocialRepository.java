package Fiscalia.junin.Repository;

import Fiscalia.junin.Model.RedSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IredSocialRepository extends JpaRepository<RedSocial, Long> {
}
