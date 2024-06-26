package barberShop.barber_shop.repositories;

import barberShop.barber_shop.domain.Service;
import barberShop.barber_shop.domain.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
   default List<Service> findByTypeService(String name) {

        Specification<Service> conjunction = ((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
        Specification<Service> spec = Specification.where(conjunction);

        if (name != null) {
            Specification<Service> nameEqual = ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("nameService"), name));
            spec.and(nameEqual);
        }

        return findAll(spec);
    }

    List<Service> findAll(Specification<Service> spec);
}
