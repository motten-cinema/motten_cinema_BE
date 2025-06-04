package reservation;

import reservation.dao.ReservationDao;
import reservation.dao.ReservationDaoImpl;
import reservation.domain.ReservationVO;

import java.sql.Timestamp;
import java.util.List;

public class ReservationTestMain {
  public static void main(String[] args) {
    ReservationDao reservationDao = new ReservationDaoImpl();

    // ✅ 1. 예매 등록 (Create)
    ReservationVO newReservation = new ReservationVO(
      "FD999",     // 예매 ID
      5,           // 상영 정보 ID
      2,           // 인원수
      20000,       // 총 금액
      new Timestamp(System.currentTimeMillis()), // 예매 시간
      "RESERVED"   // 상태
    );
    reservationDao.save(newReservation);
    System.out.println("✅ 예매 등록 완료");

    // ✅ 2. 전체 조회 (Read All)
    System.out.println("📦 전체 예매 목록:");
    List<ReservationVO> allReservations = reservationDao.findAll();
    for (ReservationVO r : allReservations) {
      printReservation(r);
    }

    // ✅ 3. 단건 조회 (Read One)
    System.out.println("\n🔍 예매 ID 'FD999'로 조회:");
    ReservationVO found = reservationDao.findById("FD999");
    if (found != null) printReservation(found);
    else System.out.println("❌ 해당 예매가 없습니다.");

    System.out.println("\n🔍 예매 ID 'FD998'로 조회:");
    ReservationVO found2 = reservationDao.findById("FD998");
    if (found2 != null) printReservation(found2);
    else System.out.println("❌ 해당 예매가 없습니다.");

    // ✅ 4. 예매 수정 (Update)
    if (found != null) {
      found.setStatus("CANCELLED");
      reservationDao.update(found);
      System.out.println("\n✏️ 예매 상태를 CANCELLED로 변경했습니다.");
    }

    // ✅ 5. 삭제 (Delete)
    reservationDao.delete("FD999");
    System.out.println("\n🗑️ 예매 ID 'FD999' 삭제 완료");

    // ✅ 최종 목록 확인
    System.out.println("\n📦 최종 예매 목록:");
    allReservations = reservationDao.findAll();
    for (ReservationVO r : allReservations) {
      printReservation(r);
    }
  }

  private static void printReservation(ReservationVO r) {
    System.out.printf("📄 ID: %s | Schedule: %d | Person: %d | Price: %d | Time: %s | Status: %s%n",
      r.getReservationId(), r.getScheduleId(), r.getTotalPerson(),
      r.getTotalPrice(), r.getReservationTime(), r.getStatus());
  }
}