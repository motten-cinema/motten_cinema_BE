package movie.service;

import movie.dao.MovieDao;
import movie.dao.MovieDaoImpl;
import movie.domain.MovieVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MovieServiceImpl implements MovieService {

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
