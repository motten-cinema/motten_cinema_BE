package cinema;

import cinema.domain.ScheduleVO;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.util.List;

public class ScheduleCSVTest {
    public static void main(String[] args) throws Exception {
        List<ScheduleVO> schedules = new CsvToBeanBuilder<ScheduleVO>(new FileReader("src/main/resources/schedule.csv"))
                .withType(ScheduleVO.class)
                .build()
                .parse();

        schedules.forEach(System.out::println);

    }
}
