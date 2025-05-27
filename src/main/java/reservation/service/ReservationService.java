package reservation.service;

import reservation.dao.ReservationDao;
import reservation.dataimport.ReservationDaoImpl;
import reservation.domain.ReservationVO;

import java.util.List;

public class ReservationService {
  private final ReservationDao dao = new ReservationDaoImpl();

  public void register(ReservationVO vo) {
    dao.save(vo);
  }

  public ReservationVO get(String id) {
    return dao.findById(id);
  }

  public List<ReservationVO> list() {
    return dao.findAll();
  }

  public void update(ReservationVO vo) {
    dao.update(vo);
  }

  public void delete(String id) {
    dao.delete(id);
  }
}