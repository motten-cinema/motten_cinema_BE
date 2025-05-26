package reservation.domain;

import java.sql.Timestamp;

public class ReservationVO {
  private String reservationId;
  private int scheduleId;
  private int totalPerson;
  private int totalPrice;
  private Timestamp reservationTime;
  private String status;

  public ReservationVO(String reservationId, int scheduleId, int totalPerson,
                       int totalPrice, Timestamp reservationTime, String status) {
    this.reservationId = reservationId;
    this.scheduleId = scheduleId;
    this.totalPerson = totalPerson;
    this.totalPrice = totalPrice;
    this.reservationTime = reservationTime;
    this.status = status;
  }

  public String getReservationId() {
    return reservationId;
  }

  public void setReservationId(String reservationId) {
    this.reservationId = reservationId;
  }

  public int getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(int scheduleId) {
    this.scheduleId = scheduleId;
  }

  public int getTotalPerson() {
    return totalPerson;
  }

  public void setTotalPerson(int totalPerson) {
    this.totalPerson = totalPerson;
  }

  public int getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(int totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Timestamp getReservationTime() {
    return reservationTime;
  }

  public void setReservationTime(Timestamp reservationTime) {
    this.reservationTime = reservationTime;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}