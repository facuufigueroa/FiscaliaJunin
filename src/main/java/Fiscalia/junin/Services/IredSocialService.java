package Fiscalia.junin.Services;


import Fiscalia.junin.Model.Causa;
import Fiscalia.junin.Model.RedSocial;

public interface IredSocialService {

    public void save(RedSocial redSocial);
    public RedSocial findById(Long id);

}
