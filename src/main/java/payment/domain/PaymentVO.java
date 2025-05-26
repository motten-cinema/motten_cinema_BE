package payment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentVO {
    private int paymentId;
    private String reservationId;
    private boolean useCashReceipt;
    private String phoneNumber;
    private LocalDateTime paymentTime;
}
