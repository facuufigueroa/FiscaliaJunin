package Fiscalia.junin.Services;

import Fiscalia.junin.Model.User;
import Fiscalia.junin.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements IUserService, UserDetailsService {

    @Autowired
    public UserRepository userRepository;
    @Override
    public boolean save(User user) {
        if (userRepository.findByEmail(user.getEmail())==null){ //se va a buscar al usuario por email en la bbdd, si no lo encuentra se va a guardar el usuario, caso contrario retorna false
            if(user.getId()!=null) {
                user.setPassword(user.getPassword());
                userRepository.save(user);
                return true;
            }
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> buscarUsuario(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void save2(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
}
