package at.ac.uibk.pm.csaz9837.exam1.e02;

import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {
        //i interpreted the corner case if start and end date are the same, then we still print the day
        LocalDate startDate = LocalDate.of(2022,1,9);
        LocalDate endDate = LocalDate.of(2022,1,15);
        for (LocalDate localdate: new DateRange(startDate, endDate)) {
            System.out.println(localdate);
        }

    }
}
