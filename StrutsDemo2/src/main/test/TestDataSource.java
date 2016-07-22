import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;

public class TestDataSource {
    @Test
    public void testDemo() {
        LocalDate startDate = LocalDate.of(2010, 7, 1);//1900年1月1日
        LocalDate endDate = LocalDate.of(2012,8,2);//2012年8月1日

        Period period = Period.between(startDate ,endDate);

        System.out.println(period.getMonths()+ " -- "+period.getYears());

    }
}
