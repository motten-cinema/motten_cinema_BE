package cinema.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationSeatVO {
    private Long reservationSeatId;
    private String reservationId;
    private Long seatId;
}
