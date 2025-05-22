package cinema.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatVO {
    private Long seatId;
    private String seatCode;
    private Boolean isReserved;

    private Long scheduleId;
}
