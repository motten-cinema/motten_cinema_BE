package cinema.dao;

import cinema.domain.SeatVO;
import database.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeatDaoImpl implements SeatDao {
    Connection conn = JDBCUtil.getConnection();
    private PreparedStatement pstmt;
    private ResultSet rs;


    @Override
    public void insert(SeatVO seat) {
        String sql = "INSERT INTO seat (schedule_id, seat_code, is_reserved) VALUES (?, ?, ?)";
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, seat.getScheduleId());
            pstmt.setString(2, seat.getSeatCode());
            pstmt.setBoolean(3, seat.getIsReserved());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateReservedStatus(int seatId, boolean isReserved) {
        String sql = "UPDATE seat SET is_reserved = ? WHERE seat_id = ?";
        try{
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setBoolean(1, isReserved);
            pstmt.setInt(2, seatId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SeatVO> findByScheduleId(int scheduleId) {
        List<SeatVO> list = new ArrayList<>();
        String sql = "SELECT * FROM seat WHERE schedule_id = ?";
        try{
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, scheduleId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(SeatVO.builder()
                        .seatId(rs.getInt("seat_id"))
                        .scheduleId(rs.getInt("schedule_id"))
                        .seatCode(rs.getString("seat_code"))
                        .isReserved(rs.getBoolean("is_reserved"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
