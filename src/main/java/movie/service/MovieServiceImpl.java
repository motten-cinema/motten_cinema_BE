package cinema.service;

import cinema.dao.MovieDao;
import cinema.dao.MovieDaoImpl;
import cinema.domain.MovieVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CinemaServiceImpl implements CinemaService {

    private final MovieDao movieDao = new MovieDaoImpl();

    @Override
    public List<MovieVO> getList() throws SQLException {
        return movieDao.getList();
    }

    @Override
    public Optional<MovieVO> getById(String movieId) throws SQLException {
        return movieDao.get(String.valueOf(movieId));
    }

    @Override
    public List<MovieVO> getSortedByRating(boolean descending) throws SQLException {
        return movieDao.getSortedByRating(descending);
    }

    @Override
    public List<MovieVO> getByGenre(String genre) throws SQLException {
        return movieDao.getByGenre(genre);
    }
}
