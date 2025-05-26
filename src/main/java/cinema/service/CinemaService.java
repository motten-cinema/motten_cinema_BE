package cinema.service;
import cinema.domain.MovieVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
public interface CinemaService {

    List<MovieVO> getList() throws SQLException;

    Optional<MovieVO> getById(String movieId) throws SQLException;

    List<MovieVO> getSortedByRating(boolean descending) throws SQLException;

    List<MovieVO> getByGenre(String genre) throws SQLException;
}