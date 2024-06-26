package barberShop.barber_shop.dto;

import barberShop.barber_shop.domain.Service;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

@Mapper
@Configuration
@Getter
@Setter
public class ServiceDto {

    private Integer id;
    private String nameService;
    private Double valor;

    public Service dtoToService (ServiceDto serviceDto) {

        return Service.builder()
                .id(serviceDto.getId())
                .nameService(serviceDto.getNameService())
                .valor(serviceDto.getValor())
                .build();
    }

}
