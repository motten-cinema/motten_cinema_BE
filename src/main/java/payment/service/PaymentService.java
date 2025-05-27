package payment.service;

import payment.dao.PaymentDao;
import payment.dao.PaymentDaoImpl;
import payment.domain.PaymentVO;

import java.sql.SQLException;

public class PaymentService implements PaymentServiceInterface {
    private PaymentDao paymentDao = new PaymentDaoImpl();


    //PaymentService는 SQL문을 활용하여 작성
    public boolean checkReservation(String reservationId) {
        try{
            if(!paymentDao.reservationExists(reservationId)){ //PaymentDao의 reservationExists 메서드를 통해 예매 번호 검증
                System.out.println("존재하지 않는 예매 번호입니다.");
                return false;
            }  System.out.println("예매번호: " + reservationId + "에 대한 결제를 진행합니다.");
            return true;
        } catch(SQLException e){
            e.printStackTrace();}
        return false;
    }

    @Override
    public boolean savePayment(PaymentVO payment){ //인터페이스 구현하는 이유? 나중에 서비스가 많아지면 관리하기 힘들어서
        try{
            int result = paymentDao.insert(payment);
            return result > 0;
        } catch (SQLException e) {
            System.out.println("결제 정보 저장 실패 :" + e.getMessage());
            return false;
        }
    }
}