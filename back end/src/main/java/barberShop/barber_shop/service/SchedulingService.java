package barberShop.barber_shop.service;

import barberShop.barber_shop.domain.Scheduling;
import barberShop.barber_shop.repositories.SchedulingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulingService {
    @Autowired
    SchedulingRepository schedulingRepository;

    public Scheduling save(Scheduling scheduling) {

        return schedulingRepository.save(scheduling);
    }
    public List<Scheduling> findAll() {
        List<Scheduling> schedulings = schedulingRepository.findAll();
        return schedulings;
    }
    public List<Scheduling> findByUserId(Integer id) {
        List<Scheduling> schedulings = schedulingRepository.findByUserId(id);

        Scheduling scheduling = schedulings.stream()
                .filter(scheduling1 -> scheduling1.getUser().getId().equals(id))
                .findFirst()
                .orElse(null);

        if (scheduling != null) {
            return schedulings;
        } else {
            return null;
        }
    }
}
