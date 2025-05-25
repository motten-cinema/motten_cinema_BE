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
    private int seatId;
    private String seatCode;
    private Boolean isReserved;

    private int scheduleId;


    @Override
    public String toString() {
        return "SeatVO{" +
                "seatId=" + seatId +
                ", scheduleId=" + scheduleId +
                ", seatCode='" + seatCode + '\'' +
                ", isReserved=" + isReserved +
                '}';
    }
}
