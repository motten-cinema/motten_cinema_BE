package payment.service;

import payment.domain.PaymentVO;

public interface PaymentServiceInterface {
    boolean savePayment(PaymentVO payment);
}