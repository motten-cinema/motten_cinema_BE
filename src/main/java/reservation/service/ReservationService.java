package reservation.service;

import reservation.domain.ReservationInfoVO;
import reservation.domain.ReservationVO;
import seat.domain.ReservationSeatVO;
import seat.domain.SeatVO;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
  // 예매 정보 저장
  void insertReservation(ReservationVO reservationVO);

  // 예매 목록 조회
  List<ReservationVO> getReservations();

  // 예매 정보 조회
  ReservationInfoVO getReservationById(String reservationId, int scheduleId);

  // 좌석 상태 변경
  void updateSeatByReservationId(String reservationId, SeatVO seatVO);

  // 예매/좌석/결제정보 삭제
  void deleteReservationById(String reservationId);

  //예매-좌석 정보를 저장
  void saveReservationWithSeats(ReservationVO reservation, List<ReservationSeatVO> seatMappings);

  ReservationVO findReservationById(String reservationId);
  List<String> getSeatCodesFromIds(List<Integer> seatIds);
//  private String reservationId;
//  private int scheduleId;
//  private int totalPerson;
//  private int totalPrice;
//  private Timestamp reservationTime;
//  private String status;

//  private final ReservationDao dao = new ReservationDaoImpl();
//
//  public void register(ReservationVO vo) {
//    dao.save(vo);
//  }
//
//  public ReservationVO get(String id) {
//    return dao.findById(id);
//  }
//
//  public List<ReservationVO> list() {
//    return dao.findAll();
//  }
//
//  public void update(ReservationVO vo) {
//    dao.update(vo);
//  }
//
//  public void delete(String id) {
//    dao.delete(id);
//  }
}