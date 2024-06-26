package barberShop.barber_shop.dto;

import barberShop.barber_shop.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

@Mapper
@Configuration
@Getter
@Setter
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private String password;

    public User dtoToUser(UserDto userDto) {

        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
}
