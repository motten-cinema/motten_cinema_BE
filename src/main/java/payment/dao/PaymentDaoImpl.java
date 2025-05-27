package payment.dao;

import payment.domain.PaymentVO;
import database.JDBCUtil;

import java.sql.*;

public class PaymentDaoImpl implements PaymentDao {

    private Connection conn = JDBCUtil.getConnection();

    //결제 정보 등록
    @Override
    public int insert(PaymentVO payment) { //payment_id는 auto_increment, setter 대신 Builder 패턴..?나중에 고민
        String sql = "INSERT INTO payment (reservation_id, use_cash_receipt, phone_number, payment_time) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, payment.getReservationId());
            pstmt.setBoolean(2, payment.isUseCashReceipt());
            pstmt.setString(3, payment.getPhoneNumber());
            pstmt.setTimestamp(4, Timestamp.valueOf(payment.getPaymentTime()));
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //reservation id 유효성 검사
    @Override
    public boolean reservationExists(String reservationId) {
        String sql = "SELECT COUNT(*) FROM reservation WHERE reservation_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, reservationId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //결제 정보 (예매 내역) 반환
    @Override
    public PaymentVO findByReservationId(String reservationId) throws SQLException {
        String sql = "SELECT * FROM payment WHERE reservation_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, reservationId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                PaymentVO payment = new PaymentVO();
                payment.setReservationId(rs.getString("reservation_id"));
                payment.setUseCashReceipt(rs.getBoolean("use_cash_receipt"));
                payment.setPhoneNumber(rs.getString("phone_number"));
                payment.setPaymentTime(rs.getTimestamp("payment_time").toLocalDateTime());
                return payment;
            }
        }
        return null; //조회 결과가 없다면 null 반환
    }
}