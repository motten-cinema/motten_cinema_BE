package movie.dataimport;

import movie.dao.MovieDao;
import movie.dao.MovieDaoImpl;
import movie.domain.MovieVO;
import com.opencsv.bean.CsvToBeanBuilder;
import database.JDBCUtil;

import java.io.InputStreamReader;
import java.util.List;

public class ImportMovieData {
    public static void main(String[] args) {
        MovieDao dao = new MovieDaoImpl();

        try (
                InputStreamReader reader = new InputStreamReader(
                        ImportMovieData.class.getClassLoader().getResourceAsStream("cinema.csv"), "UTF-8")
        ) {
            List<MovieVO> movies = new CsvToBeanBuilder<MovieVO>(reader)
                    .withType(MovieVO.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();

            for (MovieVO movie : movies) {
                dao.insert(movie);
                System.out.println("Inserted: " + movie.getTitle());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
