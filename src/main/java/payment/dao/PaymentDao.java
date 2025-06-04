package payment.dao;

import payment.domain.PaymentVO;
import java.sql.SQLException;

public interface PaymentDao {

    int insert(PaymentVO payment) throws SQLException;
    PaymentVO getByReservationId(String reservationId) throws SQLException;
    boolean reservationExists(String reservationId);
    void deletePaymentByReservationId(String reservationId);
}

