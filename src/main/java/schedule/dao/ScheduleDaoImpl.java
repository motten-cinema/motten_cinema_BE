package schedule.dao;

import schedule.domain.ScheduleVO;
import database.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScheduleDaoImpl implements ScheduleDao {
    Connection conn = JDBCUtil.getConnection();

    // schedule 테이블 관련 sql 명령어
    private String SCHEDULE_INSERT = "INSERT INTO schedule (schedule_id, movie_id, screen_date, start_time, end_time) VALUES (?, ?, ?, ?, ?)";
    private String SCHEDULE_LIST = "SELECT * FROM schedule";
    private String SCHEDULE_GET = "SELECT * FROM schedule WHERE schedule_id = ?";
    private String SCHEDULE_UPDATE = "UPDATE schedule SET screen_date = ?, start_time = ?, end_time = ? WHERE schedule_id = ?";
    private String SCHEDULE_DELETE = "DELETE FROM schedule WHERE schedule_id = ?";

    // 상영 정보 추가
    @Override
    public void addSchedule(ScheduleVO schedule) {
        try(PreparedStatement pstmt = conn.prepareStatement(SCHEDULE_INSERT)) {
            pstmt.setInt(1, schedule.getScheduleId());
            pstmt.setInt(2, schedule.getMovieId());
            // VO에는 LocalDate, LocalTime을 이용하지만 setLocalDate, setLocalTime이 없다.
            // 따라서 valueOf를 이용해서 Date와 Time 타입으로 바꿔주었다.
            pstmt.setDate(3, Date.valueOf(schedule.getScreenDate()));
            pstmt.setTime(4, Time.valueOf(schedule.getStartTime()));
            pstmt.setTime(5, Time.valueOf(schedule.getEndTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ResultSet을 ScheduleVO 객체로 변환하는 map 함수
    private ScheduleVO map(ResultSet rs) throws SQLException {
        ScheduleVO schedule = new ScheduleVO();
        schedule.setScheduleId(rs.getInt("schedule_id"));
        schedule.setMovieId(rs.getInt("movie_id"));
        // getLocalDate와 getLocalTime 또한 없으므로, toLocalDate(), toLocalTime()을 활용한다.
        schedule.setScreenDate(rs.getDate("screen_date").toLocalDate());
        schedule.setStartTime(rs.getTime("start_time").toLocalTime());
        schedule.setEndTime(rs.getTime("end_time").toLocalTime());
        return schedule;
    }

    // 상영 목록 조회
    @Override
    public List<ScheduleVO> getSchedule() throws SQLException {
        List<ScheduleVO> scheduleList = new ArrayList<>();
        Connection conn = JDBCUtil.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(SCHEDULE_LIST);
            ResultSet rs = pstmt.executeQuery()) {
            while(rs.next()) {
                ScheduleVO schedule = map(rs);
                scheduleList.add(schedule);
            }
        }
        return scheduleList;
    }

    // 상영 정보 조회
    @Override
    public Optional<ScheduleVO> getScheduleById(int scheduleId) {
        try(PreparedStatement pstmt = conn.prepareStatement(SCHEDULE_GET)) {
            pstmt.setInt(1, scheduleId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()) {
                    return Optional.of(map(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // 상영 정보 수정
    @Override
    public int updateSchedule(ScheduleVO schedule) throws SQLException {
        Connection conn = JDBCUtil.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(SCHEDULE_UPDATE)) {
            pstmt.setDate(1, Date.valueOf(schedule.getScreenDate()));
            pstmt.setTime(2, Time.valueOf(schedule.getStartTime()));
            pstmt.setTime(3, Time.valueOf(schedule.getEndTime()));
            return pstmt.executeUpdate();
        }
    }

    @Override
    public int deleteSchedule(int scheduleId) throws SQLException {
        try(PreparedStatement pstmt = conn.prepareStatement(SCHEDULE_DELETE)) {
            pstmt.setInt(1, scheduleId);
            return pstmt.executeUpdate();
        }
    }

    @Override
    public List<ScheduleVO> getSchedulesByMovieId(int movieId) throws SQLException {
        List<ScheduleVO> scheduleList = new ArrayList<>();
        String sql = "SELECT * FROM schedule WHERE movie_id = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, movieId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    scheduleList.add(map(rs));
                }
            }
        }
        return scheduleList;
    }
}
