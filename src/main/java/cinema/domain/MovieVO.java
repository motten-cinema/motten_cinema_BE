package cinema.domain;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieVO {

    @CsvBindByName(column = "movie_id")
    private int movie_id;

    @CsvBindByName(column = "title")
    private String title;

    @CsvBindByName(column = "genre")
    private String genre;

    @CsvBindByName(column = "rating")
    private float rating;

    @CsvBindByName(column = "director")
    private String director;

    @CsvBindByName(column = "cast")
    private String cast;

    @CsvBindByName(column = "summary")
    private String summary;

    @CsvBindByName(column = "age_limit")
    private int age_limit;

    @CsvBindByName(column = "duration")
    private int duration;

    @CsvBindByName(column = "release_date")
    @CsvDate("yyyy-MM-dd")
    private LocalDate release_date;
}
