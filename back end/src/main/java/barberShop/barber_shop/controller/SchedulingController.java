package barberShop.barber_shop.controller;

import barberShop.barber_shop.domain.Scheduling;
import barberShop.barber_shop.domain.Service;
import barberShop.barber_shop.domain.User;
import barberShop.barber_shop.dto.SchedulingDto;
import barberShop.barber_shop.service.SchedulingService;
import barberShop.barber_shop.service.ServiceOfService;
import barberShop.barber_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/scheduling")
@CrossOrigin("*")
public class SchedulingController {

    @Autowired
    SchedulingService schedulingService;

    @Autowired
    UserService userService;

    @Autowired
    SchedulingDto schedulingDto;

    @Autowired
    ServiceOfService serviceOfService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody SchedulingDto schedulingDto) {

        Optional<User> user = userService.findById(schedulingDto.getUser().getId());
        String userName = user.get().getName();
        schedulingDto.setUserName(userName);

        Optional<Service> service = serviceOfService.findById(schedulingDto.getService().getId());
        String typeService = service.get().getNameService();
        Double price = service.get().getValor();

        schedulingDto.setPrice(price);
        schedulingDto.setTypeService(typeService);

        List<Scheduling> VERIFY = schedulingService.findAll();

        Scheduling schedulings = VERIFY.stream()
                        .filter(scheduling1 -> scheduling1.getDate().equals(schedulingDto.getDate()) && scheduling1.getTime().equals(schedulingDto.getTime()))
                        .findFirst()
                        .orElse(null);

        if (schedulings == null) {
            Scheduling scheduling = schedulingDto.dtoToScheduling(schedulingDto);
            return ResponseEntity.ok().body(schedulingService.save(scheduling));
        } else {
            return ResponseEntity.notFound().build();
        }
     }

    @GetMapping("/findAll")
    public List<Scheduling> findAll() {
        return schedulingService.findAll();
    }
    @GetMapping("/findByUserId")
    public ResponseEntity<List<Scheduling>> findScheduling(@RequestParam(value = "userId") Integer id) {

        List<Scheduling> found = schedulingService.findByUserId(id);

        return ResponseEntity.ok().body(found);
    }
}
