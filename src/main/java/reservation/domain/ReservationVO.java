package reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationVO {
  private String reservationId;
  private int scheduleId;
  private int totalPerson;
  private int totalPrice;
  private Timestamp reservationTime;
  private String status;
}