package command;

import command.util.InputUtil;
import command.print.MovieViewImpl;
import command.print.ReservationViewImpl;
import lombok.RequiredArgsConstructor;
import movie.domain.MovieVO;
import movie.service.MovieService;
import movie.service.MovieServiceImpl;
import payment.domain.PaymentVO;
import payment.service.PaymentService;
import reservation.domain.ReservationVO;
import reservation.service.ReservationService;
import schedule.domain.ScheduleVO;
import schedule.service.ScheduleService;
import seat.domain.ReservationSeatVO;
import seat.service.SeatService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static command.util.ConsoleUtil.printLine;


//영화 예매
@RequiredArgsConstructor
public class ReserveTicketCommand implements Command {
    private final ScheduleService scheduleService;
    private final SeatService seatService;
    private final ReservationService reservationService;
    private final PaymentService paymentService = new PaymentService();
    private final MovieService movieService = new MovieServiceImpl();

    @Override
    public void execute() {
        ReservationViewImpl.printMovieHeader();
        printLine();

        System.out.println("1. 📖 상세보기");
        System.out.println("2. 🎫 예매 바로 진행");
        System.out.println("[Q] 🏠 홈으로");
        String subChoice = InputUtil.nextInput("\u001B[35m 👉 메뉴 번호를 입력해주세요: \u001B[0m").trim();

        if (subChoice.equalsIgnoreCase("Q")) {
            System.out.println("🏠 홈으로 돌아갑니다.");
            return;
        }

        switch (subChoice) {
            case "1" -> startDetailView();
            case "2" -> startReservation();
            default -> System.out.println("❌ 올바른 메뉴 번호를 입력해주세요.");
        }
    }

    private void startDetailView() {
            MovieViewImpl.printMovieList(); // 영화 목록 출력

                    // 영화 ID 입력
                    String idInput = InputUtil.nextInput("🎬 상세보기를 원하시는 영화의 ID를 입력해주세요\n" +
                            "\u001B[35m 👉 입력: ").trim();
                    if (idInput.equalsIgnoreCase("Q")) return;

                    try {
                        int movieId = Integer.parseInt(idInput);
                        Optional<MovieVO> movie = movieService.getById(movieId);
                        if (movie.isPresent()) {
                            MovieViewImpl.printMovieDetail(movieId);

                            // 홈으로 대기
                            while (true) {
                                String back = InputUtil.nextInput("[Q] 🏠 홈으로: ").trim();
                                if (back.equalsIgnoreCase("Q")) {
                                    return;
                                } else {
                                    System.out.println("❗ Q를 입력하셔야 홈으로 돌아갑니다.");
                                }
                            }
                        } else {
                            System.out.println("""
                                    ❌ 해당 ID의 영화를 찾을 수 없습니다.
                                       😠😠😠 홈으로 돌아갑니다! \n
                                    """);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ 숫자를 입력하거나 Q를 입력하세요.");
                    }


    }


    private void startReservation() {
        while (true) {
            System.out.println("🎫 예매할 영화의 ID를 입력해주세요");
            String input = InputUtil.nextInput("\u001B[35m 👉 입력:  \u001B[0m");

            if (input.equalsIgnoreCase("Q")) {
                System.out.println("🏠 홈으로 돌아갑니다.");
                return;
            }

            try {
                int movieId = Integer.parseInt(input);
                List<ScheduleVO> schedules = scheduleService.getSchedulesByMovieId(movieId);
                if (schedules.isEmpty()) {
                    System.out.println("해당 영화의 상영 일정이 없습니다.");
                    continue;
                }

                List<LocalDate> dates = schedules.stream()
                        .map(ScheduleVO::getScreenDate)
                        .distinct()
                        .sorted()
                        .toList();

                ReservationViewImpl.printDateSelectionMenu(dates);
                int dateOption = InputUtil.getIntInRange("\u001B[35m 👉 입력:  \u001B[0m", 1, dates.size());
                LocalDate selectedDate = dates.get(dateOption - 1);

                List<ScheduleVO> times = schedules.stream()
                        .filter(s -> s.getScreenDate().equals(selectedDate))
                        .sorted(Comparator.comparing(ScheduleVO::getStartTime))
                        .toList();

                ReservationViewImpl.printTimeSelection(times);
                int timeOption = InputUtil.getIntInRange("\u001B[35m 👉 입력:  \u001B[0m", 1, times.size());
                ScheduleVO selectedSchedule = times.get(timeOption - 1);
                int scheduleId = selectedSchedule.getScheduleId();

                Map<String, Boolean> seatMap = seatService.getSeatStatusMap(scheduleId);
                ReservationViewImpl.printSeatLayout(seatMap);

                List<String> validCodes = seatMap.keySet().stream().toList();
                List<String> selectedCodes;

                while (true) {
                    String[] seatCodes = InputUtil.nextSeatCodes("\u001B[35m 👉 예매할 좌석을 입력해주세요 (예: A1 A3):  \u001B[0m");
                    selectedCodes = List.of(seatCodes);

                    // 입력한 좌석 중 예약된 좌석이 있는지 검사
                    boolean hasReserved = selectedCodes.stream()
                            .anyMatch(code -> !seatMap.containsKey(code) || seatMap.get(code));

                    if (hasReserved) {
                        System.out.println("❌ 입력한 좌석 중 선택 불가한 좌석이 있습니다. 다시 선택해주세요.");
                        continue;
                    }
                    break;
                }

                int personCount = selectedCodes.size();
                int totalPrice = personCount * 12000;
                String reservationId = "FD" + (int)(Math.random() * 900 + 100);

                ReservationVO reservation = new ReservationVO(
                        reservationId,
                        scheduleId,
                        personCount,
                        totalPrice,
                        Timestamp.valueOf(LocalDateTime.now()),
                        "예매완료"
                );

                List<ReservationSeatVO> reservationSeats = seatService.convertSeatCodesToReservationSeats(reservationId, selectedCodes);
                reservationService.saveReservationWithSeats(reservation, reservationSeats);

                ReservationViewImpl.printPaymentInfo("", selectedSchedule.getScreenDate(), selectedSchedule.getStartTime(), personCount, selectedCodes, totalPrice);
                String confirm = InputUtil.nextInput("\u001B[35m 👉 결제를 진행하시겠습니까? (y/n):  \u001B[0m");
                if (!confirm.equalsIgnoreCase("y")) {
                    System.out.println("❌ 결제가 취소되었습니다.");
                    return;
                }

                boolean useReceipt = false;
                String phone = null;
                String receipt = InputUtil.nextInput("\u001B[35m 🧾 현금영수증을 발급하시겠습니까? (y/n):  \u001B[0m");
                if (receipt.equalsIgnoreCase("y")) {
                    phone = InputUtil.nextInput("\u001B[35m 📱 전화번호를 입력해주세요:  \u001B[0m");
                    useReceipt = true;
                    System.out.println("✅ 현금영수증 발급 완료 (전화번호: " + phone + ")");
                }

                try {
                    ReservationViewImpl.printPaymentProcess(reservationId, LocalDateTime.now());
                } catch (SQLException e) {
                    System.out.println("⚠️ 결제 처리 중 오류 발생: " + e.getMessage());
                }

                PaymentVO payment = PaymentVO.builder()
                        .reservationId(reservationId)
                        .useCashReceipt(useReceipt)
                        .phoneNumber(phone)
                        .paymentTime(LocalDateTime.now())
                        .build();

                boolean saved = paymentService.savePayment(payment);
                if (saved) {
                    System.out.println("✅ 결제 정보가 저장되었습니다. 예매 완료!");
                } else {
                    System.out.println("⚠️ 결제 정보 저장 실패.");
                }

                return;
            } catch (NumberFormatException e) {
                System.out.println("❌ 숫자를 입력하거나 Q를 입력하세요.");
            }
        }
    }
}