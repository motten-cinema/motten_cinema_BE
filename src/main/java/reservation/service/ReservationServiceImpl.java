package reservation.service;

import movie.dao.MovieDao;
import movie.dao.MovieDaoImpl;
import movie.domain.MovieVO;
import payment.dao.PaymentDao;
import payment.dao.PaymentDaoImpl;
import reservation.dao.ReservationDao;
import reservation.dao.ReservationDaoImpl;
import reservation.domain.ReservationInfoVO;
import reservation.domain.ReservationVO;
import schedule.dao.ScheduleDao;
import schedule.dao.ScheduleDaoImpl;
import schedule.domain.ScheduleVO;
import seat.dao.ReservationSeatDao;
import seat.dao.ReservationSeatDaoImpl;
import seat.dao.SeatDao;
import seat.dao.SeatDaoImpl;
import seat.domain.ReservationSeatVO;
import seat.domain.SeatVO;

import java.util.List;
import java.util.Optional;

public class ReservationServiceImpl implements ReservationService {
    private final ReservationDao reservationDao = new ReservationDaoImpl();
    private final ReservationSeatDao reservationSeatDao = new ReservationSeatDaoImpl();
    private final SeatDao seatDao = new SeatDaoImpl();
    private final PaymentDao paymentDao = new PaymentDaoImpl();
    private final ScheduleDao scheduleDao = new ScheduleDaoImpl();
    private final MovieDao movieDao = new MovieDaoImpl();

    @Override
    public void insertReservation(ReservationVO reservationVO) {
        reservationDao.save(reservationVO);
    }

    @Override
    public ReservationInfoVO getReservationById(String reservationId, int scheduleId) {
        ReservationInfoVO reservationInfo = new ReservationInfoVO();
        // scheduleId로 movieId, screenDate, startTime 가져오기
        Optional<ScheduleVO> scheduleResult = scheduleDao.getScheduleById(scheduleId);
        if(scheduleResult.isPresent()) {
            ScheduleVO schedule = scheduleResult.get();
            int movieId = schedule.getMovieId();
            Optional<MovieVO> movieResult = movieDao.get(movieId);
            if(movieResult.isPresent()) {
                reservationInfo.setTitle(movieResult.get().getTitle());
            }
            reservationInfo.setScreenDate(schedule.getScreenDate());
            reservationInfo.setStartTime(schedule.getStartTime());
        }

        // reservationId로 totalPerson, totalPrice 가져오기
        reservationInfo.setTotalPerson(reservationDao.findById(reservationId).getTotalPerson());
        reservationInfo.setTotalPrice(reservationDao.findById(reservationId).getTotalPrice());

        // reservationSeatDao에서 reservationId로 seats 가져오기
        reservationInfo.setSeats(reservationSeatDao.findSeatIdsByReservationId(reservationId));
        return reservationInfo;
    }

    @Override
    public void updateSeatByReservationId(String reservationId, SeatVO seatVO) {
        List<Integer> reservedSeats = reservationSeatDao.findSeatIdsByReservationId(reservationId);
        for (Integer seatId : reservedSeats) {
            seatDao.updateReservedStatus(seatId, true);
        }
    }

    @Override
    public void deleteReservationById(String reservationId) {
        reservationSeatDao.deleteByReservationId(reservationId);
        paymentDao.deletePaymentByReservationId(reservationId);
        reservationDao.delete(reservationId);
    }

    @Override
    public void saveReservationWithSeats(ReservationVO reservation, List<ReservationSeatVO> seatMappings) {
        try {
            reservationDao.saveToDB(reservation); // ← 여기만 DB 저장으로 변경

            for (ReservationSeatVO mapping : seatMappings) {
                reservationSeatDao.insert(mapping);
                seatDao.updateReservedStatus(mapping.getSeatId(), true);
            }

        } catch (Exception e) {
            throw new RuntimeException("예매 좌석 삽입 실패: " + e.getMessage(), e);
        }
    }
}
