package movie.dao;
import movie.domain.MovieVO;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MovieDao {
    void insert(MovieVO movie) throws SQLException;
    List<MovieVO> getList() throws SQLException;
    Optional<MovieVO> get(int movieId);
    List<MovieVO> getSortedByRating(boolean descending) throws SQLException;
    List<MovieVO> getByGenre(String genre) throws SQLException;
}