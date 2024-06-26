package barberShop.barber_shop.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String nameService;

    @Column
    private Double valor;
}
