package cinema.domain;

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
    private LocalDate screen_date;
    private LocalTime start_time;
    private LocalTime end_time;
}
