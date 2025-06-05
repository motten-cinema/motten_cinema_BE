package schedule.dataimport;

import schedule.dao.ScheduleDao;
import schedule.dao.ScheduleDaoImpl;
import schedule.domain.ScheduleVO;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;
import database.JDBCUtil;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ImportScheduleData {
    public static void main(String[] args) throws CsvValidationException, IOException {
        ScheduleDao dao = new ScheduleDaoImpl();

        List<ScheduleVO> schedules = new CsvToBeanBuilder<ScheduleVO>(new FileReader("src/main/resources/schedule.csv"))
                .withType(ScheduleVO.class)
                .build()
                .parse();

        schedules.forEach(schedule -> {
            System.out.println(schedule);
            dao.addSchedule(schedule);
        });


    }
}
