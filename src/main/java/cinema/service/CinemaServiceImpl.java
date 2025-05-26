package cinema.service;

import cinema.dao.CinemaDao;
import cinema.dao.CinemaDaoImpl;
import cinema.domain.MovieVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CinemaServiceImpl implements CinemaService {

    private final CinemaDao cinemaDao = new CinemaDaoImpl();

    @Override
    public List<MovieVO> getList() throws SQLException {
        return cinemaDao.getList();
    }

    @Override
    public Optional<MovieVO> getById(String movieId) throws SQLException {
        return cinemaDao.get(String.valueOf(movieId));
    }

    @Override
    public List<MovieVO> getSortedByRating(boolean descending) throws SQLException {
        return cinemaDao.getSortedByRating(descending);
    }

    @Override
    public List<MovieVO> getByGenre(String genre) throws SQLException {
        return cinemaDao.getByGenre(genre);
    }
}
