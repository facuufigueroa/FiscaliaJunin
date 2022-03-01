package Fiscalia.junin.Services;


import Fiscalia.junin.Model.Causa;
import Fiscalia.junin.Model.RedSocial;
import Fiscalia.junin.Repository.IredSocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IredSocialServiceImp implements IredSocialService{


    @Autowired
    IredSocialRepository iredSocialRepository;

    @Override
    public void save(RedSocial redSocial) {
        iredSocialRepository.save(redSocial);
    }

    @Override
    public RedSocial findById(Long id) {
        return iredSocialRepository.findById(id).orElse(null);
    }


}
