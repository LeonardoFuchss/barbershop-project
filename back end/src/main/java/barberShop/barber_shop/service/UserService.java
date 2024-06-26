package barberShop.barber_shop.service;


import barberShop.barber_shop.domain.User;
import barberShop.barber_shop.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user); // Se a verificação for nula, ou seja, o email não existir, salva no banco de dados!
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public User getByEmail(String email) {
     return userRepository.findByEmail(email);
    }
    public List<User> search(String email) {
        return userRepository.findByTypeService(email);
    }

}
