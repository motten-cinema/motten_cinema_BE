package cinema.dataimport;

import cinema.dao.CinemaDao;
import cinema.dao.CinemaDaoImpl;
import cinema.domain.MovieVO;
import com.opencsv.bean.CsvToBeanBuilder;
import database.JDBCUtil;

import java.io.InputStreamReader;
import java.util.List;

public class ImportMovieData {
    public static void main(String[] args) {
        CinemaDao dao = new CinemaDaoImpl();

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

            JDBCUtil.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
