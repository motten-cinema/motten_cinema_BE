package reservation.dao;

import database.JDBCUtil;
import movie.domain.MovieVO;
import reservation.domain.ReservationVO;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReservationDaoImpl implements ReservationDao {

  private static final String FILE_PATH = "src/main/resources/data/reservation.csv";

  @Override
  public void save(ReservationVO r) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
      writer.write(String.join(",", Arrays.asList(
        r.getReservationId(),
        String.valueOf(r.getScheduleId()),
        String.valueOf(r.getTotalPerson()),
        String.valueOf(r.getTotalPrice()),
        r.getReservationTime().toString(),
        r.getStatus()
      )));
      writer.newLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public ReservationVO findById(String reservationId) {
    String sql = "SELECT * FROM reservation WHERE reservation_id  = ?";
    try (Connection conn = JDBCUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, reservationId);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          return new ReservationVO(
                  rs.getString("reservation_id"),
                  rs.getInt("schedule_id"),
                  rs.getInt("total_person"),
                  rs.getInt("total_price"),
                  rs.getTimestamp("reservation_time"),
                  rs.getString("status")
          );
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("DB에서 예매 정보 조회 실패: " + e.getMessage(), e);
    }
    return null; // 조회 결과 없을 경우
  }

  private ReservationVO map(ResultSet rs) throws SQLException {
    ReservationVO reservation = new ReservationVO();
    reservation.setReservationId(rs.getString("reservation_id"));
    reservation.setScheduleId(rs.getInt("schedule_id"));
    reservation.setTotalPerson(rs.getInt("total_person"));
    reservation.setTotalPrice(rs.getInt("total_price"));
    reservation.setReservationTime(rs.getTimestamp("reservation_time"));
    reservation.setStatus(rs.getString("status"));
    return reservation;
  }

  @Override
  public List<ReservationVO> findAll() {
    String sql = "SELECT * FROM reservation";
    List<ReservationVO> reservationList = new ArrayList<>();
    try (Connection conn = JDBCUtil.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        reservationList.add(map(rs));
      }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    return reservationList;
  }

  @Override
  public void update(ReservationVO updated) {
    List<ReservationVO> all = findAll();
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
      for (ReservationVO r : all) {
        if (r.getReservationId().equals(updated.getReservationId())) {
          r = updated;
        }
        writer.write(String.join(",", Arrays.asList(
          r.getReservationId(),
          String.valueOf(r.getScheduleId()),
          String.valueOf(r.getTotalPerson()),
          String.valueOf(r.getTotalPrice()),
          r.getReservationTime().toString(),
          r.getStatus()
        )));
        writer.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(String reservationId) {
    String sql = "DELETE FROM reservation WHERE reservation_id = ?";
    try (Connection conn = JDBCUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, reservationId);
      pstmt.executeUpdate();

    } catch (SQLException e) {
      throw new RuntimeException(" 예약 삭제 실패: " + e.getMessage(), e);
    }
  }

  @Override
  public void saveToDB(ReservationVO r) {
    String sql = "INSERT INTO reservation (reservation_id, schedule_id, total_person, total_price, reservation_time, status) VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection conn = JDBCUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, r.getReservationId());
      pstmt.setInt(2, r.getScheduleId());
      pstmt.setInt(3, r.getTotalPerson());
      pstmt.setInt(4, r.getTotalPrice());
      pstmt.setTimestamp(5, r.getReservationTime());
      pstmt.setString(6, r.getStatus());

      pstmt.executeUpdate();

    } catch (SQLException e) {
      throw new RuntimeException("예약 정보 DB 저장 실패: " + e.getMessage(), e);
    }
  }
}