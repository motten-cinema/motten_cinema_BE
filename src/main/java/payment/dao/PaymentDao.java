package payment.dao;

import payment.domain.PaymentVO;
import java.sql.SQLException;

public interface PaymentDao {
    int insert(PaymentVO payment) throws SQLException; //결제 정보 등록
    boolean reservationExists(String reservationId) throws SQLException; //reservationId 유효성 검사
    PaymentVO findByReservationId(String reservationId) throws SQLException; //결제(예매) 내역 반환
}