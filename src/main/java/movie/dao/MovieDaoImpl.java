package movie.dao;

import movie.domain.MovieVO;
import database.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieDaoImpl implements MovieDao {

    private final Connection conn = JDBCUtil.getConnection();

    @Override
    public void insert(MovieVO movie) throws SQLException {
        String sql = "INSERT INTO movie (movieId, title, genre, rating, director, cast, summary, ageLimit, duration, releaseDate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, movie.getMovieId());
            ps.setString(2, movie.getTitle());
            ps.setString(3, movie.getGenre());
            ps.setFloat(4, movie.getRating());
            ps.setString(5, movie.getDirector());
            ps.setString(6, movie.getCast());
            ps.setString(7, movie.getSummary());
            ps.setInt(8, movie.getAgeLimit());
            ps.setInt(9, movie.getDuration());
            ps.setDate(10, Date.valueOf(movie.getReleaseDate()));
            ps.executeUpdate();
        }
    }

    @Override
    public List<MovieVO> getList() throws SQLException {
        String sql = "SELECT * FROM movie";
        List<MovieVO> movieList = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                movieList.add(map(rs));
            }
        }
        return movieList;
    }

    @Override
    public Optional<MovieVO> get(int movieId) {
        String sql = "SELECT * FROM movie WHERE movie_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, movieId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(map(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<MovieVO> getSortedByRating(boolean descending) throws SQLException {
        String sql = descending
                ? "SELECT * FROM movie ORDER BY rating DESC"
                : "SELECT * FROM movie ORDER BY rating ASC";
        List<MovieVO> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    @Override
    public List<MovieVO> getByGenre(String genre) throws SQLException {
        String sql = "SELECT * FROM movie WHERE genre = ?";
        List<MovieVO> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, genre);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(map(rs));
                }
            }
        }
        return list;
    }

    private MovieVO map(ResultSet rs) throws SQLException {
        MovieVO movie = new MovieVO();
        movie.setMovieId(rs.getInt("movie_id"));
        movie.setTitle(rs.getString("title"));
        movie.setGenre(rs.getString("genre"));
        movie.setRating(rs.getFloat("rating"));
        movie.setDirector(rs.getString("director"));
        movie.setCast(rs.getString("cast"));
        movie.setSummary(rs.getString("summary"));
        movie.setAgeLimit(rs.getInt("age_limit"));
        movie.setDuration(rs.getInt("duration"));
        movie.setReleaseDate(rs.getDate("release_date").toLocalDate());
        return movie;
    }
}
