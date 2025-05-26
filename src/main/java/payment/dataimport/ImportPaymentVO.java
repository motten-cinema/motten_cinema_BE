package payment.dataimport;

import payment.dao.PaymentDao;
import payment.dao.PaymentDaoImpl;
import payment.domain.PaymentVO;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;
import database.JDBCUtil;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ImportPaymentVO {
    public static void main(String[] args) throws IOException, CsvValidationException {
        PaymentDao paymentDao = new PaymentDaoImpl();

        List<PaymentVO> payments = new CsvToBeanBuilder<PaymentVO>(new FileReader("src/main/resources/payment.csv"))
                .withType(PaymentVO.class)
                .build()
                .parse();

        payments.forEach(payment -> {
            System.out.println(payment);
            try {
                paymentDao.insert(payment);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });
        JDBCUtil.close();
    }
}
