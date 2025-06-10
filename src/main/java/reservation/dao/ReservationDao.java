package reservation.dao;

import reservation.domain.ReservationVO;
import java.util.List;

public interface ReservationDao {
  void save(ReservationVO reservation);
  ReservationVO findById(String reservationId);
  List<ReservationVO> findAll();
  void update(ReservationVO reservation);
  void delete(String reservationId);
  void saveToDB(ReservationVO r);
}