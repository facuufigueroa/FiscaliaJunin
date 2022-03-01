package Fiscalia.junin.Services;


import Fiscalia.junin.Model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public boolean save(User user);
    public List<User> findAll();
    public Optional<User> buscarUsuario(Long id);
    public void delete(Long id);
    public void save2(User user);
}
