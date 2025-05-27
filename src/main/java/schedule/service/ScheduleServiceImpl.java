package schedule.service;

import schedule.dao.ScheduleDao;
import schedule.dao.ScheduleDaoImpl;
import schedule.domain.ScheduleVO;
import seat.dao.SeatDao;
import seat.dao.SeatDaoImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleDao scheduleDao = new ScheduleDaoImpl();

    @Override
    public List<ScheduleVO> getSchedulesByDate(LocalDate date) {
        try{
            return scheduleDao.getSchedule().stream()
                    .filter(schedule-> schedule.getScreen_date().equals(date))
                    .collect(Collectors.toList());
        }catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}