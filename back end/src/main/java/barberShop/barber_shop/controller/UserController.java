package barberShop.barber_shop.controller;

import barberShop.barber_shop.domain.User;
import barberShop.barber_shop.dto.UserDto;
import barberShop.barber_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserDto userDto;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody UserDto userDto) {
        User userSave = userDto.dtoToUser(userDto);
        return ResponseEntity.ok(userService.saveUser(userSave));
    }

    @GetMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }
    
    @GetMapping("{id}")
    public Optional<User> findById(@PathVariable("id") Integer id) {
       return userService.findById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<Integer> search(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password){
        List<User> userFind = userService.search(email);

        if (userFind.getFirst().getPassword().equals(password)) {
            var id = userFind.getFirst().getId();
            return ResponseEntity.ok().body(id);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
