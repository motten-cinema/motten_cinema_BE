package cinema.service;

public interface CinemaService {

    //영화 전체 목록 출력하기
    void getList();
    //특정 영화 id 상세보기 출력하기
    void getById();
    //영화 별점순으로 정렬하여 출력하기
    void getSortedByRating();
    // 영화 장르별로 필터링하여 출력하기
    void getByGenre();
}
