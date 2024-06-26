package barberShop.barber_shop.service;

import barberShop.barber_shop.domain.Service;
import barberShop.barber_shop.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceOfService {
    @Autowired
    ServiceRepository serviceRepository;
    public Service save (Service service) {
        return serviceRepository.save(service);
    }
    public Optional<Service> findById(Integer id) {
        return serviceRepository.findById(id);
    }
    public List<Service> findByType(String name) {
    List<Service> services = serviceRepository.findByTypeService(name);

        return services;
    }
}
