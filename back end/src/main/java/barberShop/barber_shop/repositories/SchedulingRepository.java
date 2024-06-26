package barberShop.barber_shop.repositories;

import barberShop.barber_shop.domain.Scheduling;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface SchedulingRepository extends JpaRepository<Scheduling, Integer> {
    default List<Scheduling> findScheduling(String userName) {

        Specification<Scheduling> conjunction = ((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
        Specification<Scheduling> spec = Specification.where(conjunction);

        if (userName != null) {
            Specification<Scheduling> nameEqual = ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userName"), userName));
            spec.and(nameEqual);
        }

        return findAll(spec);
    }
    List<Scheduling> findAll(Specification<Scheduling> spec);

    List<Scheduling> findByUserId(Integer id);
}
