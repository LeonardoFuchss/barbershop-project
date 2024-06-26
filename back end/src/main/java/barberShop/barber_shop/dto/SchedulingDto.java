package barberShop.barber_shop.dto;

import barberShop.barber_shop.domain.Scheduling;
import barberShop.barber_shop.domain.Service;
import barberShop.barber_shop.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;

@Mapper
@Getter
@Setter
@Configuration
public class SchedulingDto {

    private Integer id;
    private User user;
    private Service service;
    private String userName;
    private String typeService;
    private Double price;
    private LocalDate date;
    private LocalTime time;

    public Scheduling dtoToScheduling(SchedulingDto schedulingDto) {

        return Scheduling.builder()
                .id(schedulingDto.getId())
                .user(schedulingDto.getUser())
                .service(schedulingDto.getService())
                .userName(schedulingDto.getUserName())
                .typeService(schedulingDto.getTypeService())
                .price(schedulingDto.getPrice())
                .date(schedulingDto.getDate())
                .time(schedulingDto.getTime())
                .build();
    }
}
