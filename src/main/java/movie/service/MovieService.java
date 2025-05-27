package movie.service;
import movie.domain.MovieVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
public interface MovieService {

    List<MovieVO> getList() throws SQLException;

    Optional<MovieVO> getById(String movieId) throws SQLException;

    List<MovieVO> getSortedByRating(boolean descending) throws SQLException;

    List<MovieVO> getByGenre(String genre) throws SQLException;
}