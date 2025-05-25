package cinema.service;

import cinema.dao.PaymentDao;
import cinema.dao.PaymentDaoImpl;
import cinema.domain.PaymentVO;
import database.JDBCUtil;

import java.time.LocalDateTime;
import java.util.Scanner;

public class PaymentService {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PaymentDao dao = new PaymentDaoImpl();
        boolean useCashReceipt = false;
        String phoneNumber = null;

        System.out.print("예매 번호를 입력하세요 (예: FD289): ");
        String reservationId = sc.nextLine();

        try {
            System.out.print("결제하시겠습니까? (y/n): ");
            String payConfirm = sc.nextLine();
            if (!payConfirm.equals("y")) {
                System.out.println("❌ 결제가 취소되었습니다.");
                return;
            }

            System.out.print("현금 영수증 발급이 필요하신가요? (y/n): ");
            String receiptConfirm = sc.nextLine();
            if (receiptConfirm.equals("y")) {
                useCashReceipt = true;
                System.out.print("전화번호를 입력해주세요: ");
                phoneNumber = sc.nextLine();
            }

            LocalDateTime now = LocalDateTime.now();

            PaymentVO payment = new PaymentVO(0, reservationId, useCashReceipt, phoneNumber, now);

            int result = dao.insert(payment);

            if (result == 1) {
                System.out.println("결제가 완료되었습니다. 감사합니다.");
                System.out.println("예매 번호: " + reservationId);
            } else {
                System.out.println("결제 저장 실패!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close();
        }
    }
}