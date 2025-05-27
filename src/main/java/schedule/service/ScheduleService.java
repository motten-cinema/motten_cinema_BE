package schedule.service;

import schedule.domain.ScheduleVO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ScheduleService {
    //날짜로 상영 정보 조회
    List<ScheduleVO> getSchedulesByDate(LocalDate date);
}
