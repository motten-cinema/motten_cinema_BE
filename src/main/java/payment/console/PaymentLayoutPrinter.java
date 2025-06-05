package payment.console;

import payment.dao.PaymentDaoImpl;
import payment.domain.PaymentVO;
import java.sql.SQLException;

public class PaymentLayoutPrinter {
    PaymentDaoImpl paymentDaoImpl = new PaymentDaoImpl();

    public void printCheckReservationId(){
        System.out.print("결제하실 예매 번호를 입력해주세요. 예(AB123) : ");
    }
    public void printUseCashReceipt(){
        System.out.println("");
        System.out.println("◉ ◉ ◉ ◉ ◉ CASH RECEIPT  ◉ ◉ ◉ ◉ ◉");
        System.out.println("----------------------------------------");
        System.out.print("\uD83D\uDCC4 현금영수증을 발급하시겠습니까? (y/n) : ");
    }

    public void printPhoneNumber(){
        System.out.println("----------------------------------------");
        System.out.println("");
        System.out.print("📱 전화번호를 입력해주세요. 예(010-1234-5678) : ");
    }

    public void printPaymentProcess(String reservationId) throws SQLException {
        System.out.println("");
        System.out.println("----------------------------------------");
        System.out.println("💳 결제 진행 중...");
        System.out.print("");
        try {
            Thread.sleep(1000);
            System.out.println("▓          10%");
            Thread.sleep(1000);
            System.out.println("▓▓▓▓▓      50%");
            Thread.sleep(1000);
            System.out.println("▓▓▓▓▓▓▓▓▓▓ 100%");
            Thread.sleep(500);
            System.out.println("");
            System.out.println("");
            System.out.println("✅ 결제가 완료되었습니다. 감사합니다!");
            PaymentVO payment = paymentDaoImpl.getByReservationId(reservationId); //payment에 reservationId값을 통해 테이블 내용 저장
            System.out.println("예몌 번호 : " + payment.getReservationId()); //예매 번호 출력
            System.out.println("결제 시각 : " + payment.getPaymentTime()); //결제 시각 출력(포멧팅에 대한 고민 필요..(T))
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}