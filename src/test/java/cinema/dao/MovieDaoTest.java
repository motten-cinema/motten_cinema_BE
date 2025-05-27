package cinema.dao;

import cinema.domain.MovieVO;
import database.JDBCUtil;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CinemaDaoTest {

    CinemaDao dao = new CinemaDaoImpl();

    @AfterAll
    static void tearDown() {
        JDBCUtil.close();
    }

    @Test
    @DisplayName("MOVIEDAO MOVIE 목록을 추출합니다")
    @Order(1)
    void getList() throws SQLException {
        List<MovieVO> list = dao.getList();
        for (MovieVO movieVO : list) {
            System.out.println(
                    "ID: " + movieVO.getMovie_id() +
                            " || Title: " + movieVO.getTitle() +
                            " || Genre: " + movieVO.getGenre() +
                            " || Rating: " + movieVO.getRating()
            );
        }
    }

    @Test
    @DisplayName("moviedao movie 특정 1건을 추출합니다.")
    @Order(2)
    void get() throws SQLException {
        MovieVO movie = dao.get("1").orElseThrow(NoSuchElementException::new);
        assertNotNull(movie);
        System.out.println(movie);
    }

    @Test
    @DisplayName("moviedao movie를 별점 순으로 정렬합니다.")
    @Order(3)
    void getSortedByRating() throws SQLException {
        List<MovieVO> listDesc = dao.getSortedByRating(true);
        List<MovieVO> listAsc = dao.getSortedByRating(false);

        assertFalse(listDesc.isEmpty(), "영화 목록이 비어있지 않아야 함.");
        assertFalse(listAsc.isEmpty(), "영화 목록이 비어있지 않아야 함.");

        System.out.println("높은 평점 순 정렬 : ");
        for (MovieVO movie : listDesc) {
            System.out.println("ID: " + movie.getMovie_id()
                    + " || Title: " + movie.getTitle()
                    + " || Director: " + movie.getDirector()
                    + " || Rating: " + movie.getRating());
        }

        System.out.println("낮은 평점 순 정렬 : ");
        for (MovieVO movie : listAsc) {
            System.out.println("ID: " + movie.getMovie_id()
                    + " || Title: " + movie.getTitle()
                    + " || Director: " + movie.getDirector()
                    + " || Rating: " + movie.getRating());
        }
    }

    @Test
    @DisplayName("영화를 특정 장르로 추출한다.")
    @Order(4)
    void getByGenre() throws SQLException {
        String genre = "범죄";
        List<MovieVO> list = dao.getByGenre(genre);

        assertFalse(list.isEmpty(), genre + " 장르 영화가 있어야 합니다.");
        System.out.println("장르: " + genre + " -> 조회 결과 : ");
        for (MovieVO movie : list) {
            System.out.println("ID: " + movie.getMovie_id()
                    + " || Title: " + movie.getTitle()
                    + " || Director: " + movie.getDirector()
                    + " || Rating: " + movie.getRating());
            assertEquals(genre, movie.getGenre(), "모든 영화 장르가 " + genre + " 이어야 함.");
        }
    }
}
