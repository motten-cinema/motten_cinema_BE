package cinema.dao;

import cinema.domain.PaymentVO;
import database.JDBCUtil;

import java.sql.*;

public class PaymentDaoImpl implements PaymentDao {
    private Connection conn;
    public PaymentDaoImpl() {
        this.conn = JDBCUtil.getConnection();
    }

    @Override
    public int insert(PaymentVO payment) throws SQLException {
        String sql = "INSERT INTO payment (reservation_id, use_cash_receipt, phone_number, payment_time) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, payment.getReservationId());
            pstmt.setBoolean(2, payment.isUseCashReceipt());
            pstmt.setString(3, payment.getPhoneNumber());
            pstmt.setTimestamp(4, Timestamp.valueOf(payment.getPaymentTime()));
            return pstmt.executeUpdate();
        }
    }


    @Override
    public PaymentVO getByReservationId(String reservationId) throws SQLException {
        String sql = "SELECT * FROM payment WHERE reservation_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, reservationId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new PaymentVO(
                        rs.getInt("payment_id"),
                        rs.getString("reservation_id"),
                        rs.getBoolean("use_cash_receipt"),
                        rs.getString("phone_number"),
                        rs.getTimestamp("payment_time").toLocalDateTime()
                );
            }
        }
        return null;
    }
}