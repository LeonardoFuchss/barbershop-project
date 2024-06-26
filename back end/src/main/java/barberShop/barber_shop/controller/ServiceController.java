package barberShop.barber_shop.controller;

import barberShop.barber_shop.domain.Service;
import barberShop.barber_shop.dto.ServiceDto;
import barberShop.barber_shop.service.ServiceOfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service")
@CrossOrigin("*")
public class ServiceController {

    @Autowired
    ServiceOfService serviceOfService;

    @Autowired
    ServiceDto serviceDto;

    @PostMapping("/save")
    public ResponseEntity<Service> save(@RequestBody ServiceDto serviceDtoReq) {
        Service saveService = serviceDto.dtoToService(serviceDtoReq);
        return ResponseEntity.ok(serviceOfService.save(saveService));
    }

    @GetMapping("{id}")
    public Optional<Service> findById(@PathVariable("id") Integer id) {
        return serviceOfService.findById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<Integer> search(@RequestParam(value = "nameService") String name){

        List<Service> services = serviceOfService.findByType(name);

        Service serviceFind = services.stream()
                .filter(service -> service.getNameService().equals(name))
                .findFirst()
                .orElse(null);

        if (serviceFind == null) {
            return ResponseEntity.notFound().build();
        }

        var id = serviceFind.getId();

        return ResponseEntity.ok().body(id);
    }
}
