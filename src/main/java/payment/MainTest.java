package payment;

import payment.console.PaymentLayoutPrinter;
import payment.domain.PaymentVO;
import payment.service.PaymentService;


import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) throws SQLException {
        PaymentLayoutPrinter paymentLayoutPrinter = new PaymentLayoutPrinter();
        PaymentService paymentService = new PaymentService();
        PaymentVO payment = new PaymentVO();
        Scanner scanner = new Scanner(System.in);

        paymentLayoutPrinter.printCheckReservationId();
        String userReservationId = scanner.nextLine();

        if(!paymentService.checkReservation(userReservationId)){ //사용자에게 입력 받은 예매번호 검증
            System.out.println("결제를 종료합니다");
            return;
        };

        paymentLayoutPrinter.printUseCashReceipt();

        String userUseCashReceiptInput = scanner.nextLine();
        boolean userUseCashReceipt = userUseCashReceiptInput.equals("y"); //사용자가 y를 입력하면 true값으로 변환하여 저장

        paymentLayoutPrinter.printPhoneNumber();

        String userPhoneNumber = scanner.nextLine();

        payment.setReservationId(userReservationId);
        payment.setUseCashReceipt(userUseCashReceipt);
        payment.setPhoneNumber(userPhoneNumber);
        payment.setPaymentTime(LocalDateTime.now());
        paymentService.savePayment(payment);

        paymentLayoutPrinter.printPaymentProcess(userReservationId);
    }
}