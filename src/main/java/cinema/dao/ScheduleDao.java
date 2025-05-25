package cinema.dao;

import cinema.domain.ScheduleVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ScheduleDao {
    // 상영 정보 추가
    void addSchedule(ScheduleVO schedule);

    // 상영 목록 조회
    List<ScheduleVO> getSchedule() throws SQLException;

    // 상영 정보 조회
    Optional<ScheduleVO> getScheduleById(int id) throws SQLException;

    // 상영 정보 수정
    int updateSchedule(ScheduleVO schedule) throws SQLException;

    // 상영 정보 삭제
    int deleteSchedule(int id) throws SQLException;
}
