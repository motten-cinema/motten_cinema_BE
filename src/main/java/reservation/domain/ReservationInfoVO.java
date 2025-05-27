package reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationInfoVO {
    private String title;
    private LocalDate screenDate;
    private LocalTime startTime;
    private int totalPerson;
    private List<Integer> seats;
    private int totalPrice;
}
