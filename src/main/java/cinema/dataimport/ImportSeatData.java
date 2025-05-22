package cinema.dataimport;

import cinema.dao.SeatDao;
import cinema.dao.SeatDaoImpl;
import cinema.domain.SeatVO;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;
import database.JDBCUtil;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ImportSeatData {
    public static void main(String[] args) throws IOException, CsvValidationException {
        SeatDao seatDao = new SeatDaoImpl();

        List<SeatVO> seats = new CsvToBeanBuilder<SeatVO>(new FileReader("경로 이따가"))
                .withType(SeatVO.class)
                .build()
                .parse();

        seats.forEach(seat -> {
            System.out.println(seat);
            seatDao.insert(seat);
        });
        JDBCUtil.close();
    }
}
