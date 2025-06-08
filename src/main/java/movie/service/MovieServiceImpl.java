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
    public List<MovieVO> getList() {
        try {
            return movieDao.getList();
        } catch (SQLException e) {
            throw new RuntimeException("영화 목록 조회 실패", e);
        }
    }

    @Override
    public Optional<MovieVO> getById(int movieId){
        return movieDao.get(movieId);
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
