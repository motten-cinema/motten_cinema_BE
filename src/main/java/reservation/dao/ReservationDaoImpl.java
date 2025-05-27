package reservation.dao;

import reservation.domain.ReservationVO;

import java.io.*;
import java.sql.Timestamp;
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
    return findAll().stream()
      .filter(r -> r.getReservationId().equals(reservationId))
      .findFirst()
      .orElse(null);
  }

  @Override
  public List<ReservationVO> findAll() {
    List<ReservationVO> list = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] tokens = line.split(",");
        if (tokens.length < 6) continue;

        list.add(new ReservationVO(
          tokens[0],
          Integer.parseInt(tokens[1]),
          Integer.parseInt(tokens[2]),
          Integer.parseInt(tokens[3]),
          Timestamp.valueOf(tokens[4]),
          tokens[5]
        ));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return list;
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
    List<ReservationVO> all = findAll();
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
      for (ReservationVO r : all) {
        if (!r.getReservationId().equals(reservationId)) {
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
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}