package cinema.dao;
import cinema.domain.MovieVO;
import database.JDBCUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Optional;

public class CinemaDaoImpl implements CinemaDao {
    Connection conn = JDBCUtil.getConnection();

    private final String MOVIE_LIST = "select * from movie";
    private final String MOVIE_GET = "select * from movie where movie_id=?";
    private final String MOVIE_SORT_BY_RATING_DESC = "select * from movie order by rating desc";
    private final String MOVIE_SORT_BY_RATING_ASC = "select * from movie order by rating asc";
    private final String MOVIE_BY_GENRE = "select * from movie where genre=?";
    @Override
    public void insert(MovieVO movie) throws SQLException {
        String sql = "INSERT INTO movie (movie_id, title, genre, rating, director, cast, summary, age_limit, duration, release_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, movie.getMovie_id());
            ps.setString(2, movie.getTitle());
            ps.setString(3, movie.getGenre());
            ps.setFloat(4, movie.getRating());
            ps.setString(5, movie.getDirector());
            ps.setString(6, movie.getCast());
            ps.setString(7, movie.getSummary());
            ps.setInt(8, movie.getAge_limit());
            ps.setInt(9, movie.getDuration());
            ps.setDate(10, java.sql.Date.valueOf(movie.getRelease_date()));
            ps.executeUpdate();
        }
    }

    @Override
    public List<MovieVO> getList() throws SQLException{
        List<MovieVO> movieList = new ArrayList<MovieVO>();
        try(PreparedStatement ps = conn.prepareStatement(MOVIE_LIST);
            ResultSet rs = ps.executeQuery();){
            while(rs.next()){
                MovieVO movie = map(rs);
                movieList.add(movie);
            }
        }
        return movieList;
    }

    private MovieVO map(ResultSet rs) throws SQLException {
        MovieVO movie = new MovieVO();
        movie.setMovie_id(rs.getInt("MOVIE_ID"));
        movie.setTitle(rs.getString("TITLE"));
        movie.setGenre(rs.getString("GENRE"));
        movie.setRating(rs.getFloat("RATING"));
        movie.setDirector(rs.getString("DIRECTOR"));
        movie.setCast(rs.getString("CAST"));
        movie.setSummary(rs.getString("SUMMARY"));
        movie.setAge_limit(rs.getInt("AGE_LIMIT"));
        movie.setDuration(rs.getInt("DURATION"));
        movie.setRelease_date(rs.getDate("RELEASE_DATE").toLocalDate());
        return movie;
    }


    @Override
    public Optional<MovieVO> get(String id) throws SQLException{
        try(PreparedStatement ps = conn.prepareStatement(MOVIE_GET)){
            ps.setString(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return Optional.of(map(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<MovieVO> getSortedByRating(boolean descending) throws SQLException{
        String sql = descending ? MOVIE_SORT_BY_RATING_DESC : MOVIE_SORT_BY_RATING_ASC;
        List<MovieVO> list = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();){
            while(rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }
    @Override
    public List<MovieVO> getByGenre(String genre) throws SQLException {
        List<MovieVO> list = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement(MOVIE_BY_GENRE)) {
            ps.setString(1, genre);
            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    list.add(map(rs));
                }
            }
        }
        return list;
    }
}

