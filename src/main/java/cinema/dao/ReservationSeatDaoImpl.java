package cinema.dao;

import cinema.domain.ReservationSeatVO;
import database.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationSeatDaoImpl implements ReservationSeatDao{

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
    public List<ReservationSeatVO> findByReservationId(String reservationId) {
        List<ReservationSeatVO> list = new ArrayList<>();
        String sql = "SELECT * FROM reservation_seat WHERE reservation_id = ?";
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, reservationId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(ReservationSeatVO.builder()
                        .reservationSeatId(rs.getInt("reservation_seat_id"))
                        .reservationId(rs.getString("reservation_id"))
                        .seatId(rs.getInt("seat_id"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException("예매 좌석 조회 실패: " + e.getMessage(), e);
        }
        return list;
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
}
