package cinema.dao;

import cinema.domain.SeatVO;
import database.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SeatDaoImpl implements SeatDao{
    Connection conn = JDBCUtil.getConnection();

    @Override
    public void insert(SeatVO seat) {
        String sql = "insert into seat (schedule_id, seat_code, is_reserved  VALUES (?, ?, ?)";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1, seat.getScheduleId());
            pstmt.setString(2, seat.getSeatCode());
            pstmt.setBoolean(3, seat.getIsReserved());
            pstmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
