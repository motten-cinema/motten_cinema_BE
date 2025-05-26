package schedule.domain;

import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleVO {
    private int schedule_id;
    private int movie_id;

    // OpenCSV는 String을 LocalDate로 자동 변환할 수 없다.
    // @CsvDate를 통해 날짜 형식을 명시하면 자동으로 LocalDate와 LocalTime으로 변환할 수 있다.
    @CsvDate("yyyy-MM-dd")
    private LocalDate screen_date;

    @CsvDate("HH:mm:ss")
    private LocalTime start_time;

    @CsvDate("HH:mm:ss")
    private LocalTime end_time;
}
