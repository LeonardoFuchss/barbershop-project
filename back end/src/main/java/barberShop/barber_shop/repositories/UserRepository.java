package barberShop.barber_shop.repositories;

import barberShop.barber_shop.domain.Service;
import barberShop.barber_shop.domain.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    default List<User> findByTypeService(String email) {
        Specification<User> conjunction = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        Specification<User> spec = Specification.where(conjunction);

        if (email != null) {
            Specification<User> emailEqual = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("email"), email);
            spec = spec.and(emailEqual); // Usando and() para combinar as especificações
        }


        // Usando findAll com Specification e Sort para aplicar a ordenação
        return findAll(spec);
    }

    List<User> findAll(Specification<User> spec);
}
