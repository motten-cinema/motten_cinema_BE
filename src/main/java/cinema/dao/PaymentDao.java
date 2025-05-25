package cinema.dao;

import cinema.domain.PaymentVO;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PaymentDao {
    int insert(PaymentVO payment) throws SQLException;
    PaymentVO getByReservationId(String reservationId) throws SQLException;
}
