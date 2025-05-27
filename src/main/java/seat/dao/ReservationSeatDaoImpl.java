package seat.dao;

import seat.domain.ReservationSeatVO;
import database.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationSeatDaoImpl implements ReservationSeatDao {

    Connection conn = JDBCUtil.getConnection();
    private PreparedStatement pstmt;
    private ResultSet rs;


    @Override
    public void insert(ReservationSeatVO reservationSeat) {
        String sql = "INSERT INTO reservation_seat (reservation_id, seat_id) VALUES (?, ?)";
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, reservationSeat.getReservationId());
            pstmt.setInt(2, reservationSeat.getSeatId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("예매 좌석 삽입 실패: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteByReservationId(String reservationId) {
        String sql = "DELETE FROM reservation_seat WHERE reservation_id = ?";
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, reservationId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("예매 좌석 삭제 실패: " + e.getMessage(), e);
        }
    }

    @Override
    public List<String> findSeatCodesByReservationId(String reservationId) {
        List<String> seatCodes = new ArrayList<>();
        String sql = "SELECT s.seat_code " +
                "FROM reservation_seat rs " +
                "JOIN seat s ON rs.seat_id = s.seat_id " +
                "WHERE rs.reservation_id = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, reservationId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    seatCodes.add(rs.getString("seat_code"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("좌석 코드 조회 실패: " + e.getMessage(), e);
        }
        return seatCodes;
    }

    @Override
    public List<Integer> findSeatIdsByReservationId(String reservationId) {
        List<Integer> seatIds = new ArrayList<>();
        String sql = "SELECT seat_id FROM reservation_seat WHERE reservation_id = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, reservationId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    seatIds.add(rs.getInt("seat_id"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("좌석 ID 조회 실패: " + e.getMessage(), e);
        }

        return seatIds;
    }
    private ReservationSeatVO map(ResultSet rs) throws SQLException {
        return ReservationSeatVO.builder()
                .reservationSeatId(rs.getInt("reservation_seat_id"))
                .reservationId(rs.getString("reservation_id"))
                .seatId(rs.getInt("seat_id"))
                .build();
    }
}
