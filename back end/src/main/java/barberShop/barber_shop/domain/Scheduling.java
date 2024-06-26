package barberShop.barber_shop.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Scheduling { // Agendamento
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private User user; // Chave estrangeira via json (ID)

    @ManyToOne
      private Service service; // Chave estrangeira via json (ID)

    // Atributo sera setados de acordo com o id do usuario buscado no banco de dados
    private String userName;

    // Atributos serão setados de acordo com o id do serviço buscado no banco de dados
    private String typeService;
    private Double price;

    // serão definidos via json
    private LocalDate date;
    private LocalTime time;




}
