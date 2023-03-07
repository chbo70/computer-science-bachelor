package at.ac.uibk.pm.csaz9837.exam1.e02;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DateRange implements Iterable<LocalDate> {
    private LocalDate startDate;
    private LocalDate endDate;

    private List<LocalDate> rangeOfDate;

    public DateRange(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        rangeOfDate = new ArrayList<>();
    }
    public List<LocalDate> getRangeOfDate() throws InvalidDateRange {
        if ((startDate.isBefore(endDate) && endDate.isAfter(startDate)) || startDate.equals(endDate)){
            for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
                rangeOfDate.add(date);
            }
            rangeOfDate.add(endDate);
            return rangeOfDate;
        } else {
            throw new InvalidDateRange();
        }

    }

    @Override
    public Iterator<LocalDate> iterator() {
        try{
            getRangeOfDate();
        } catch (InvalidDateRange e){
            e.printException();
        }
        return rangeOfDate.iterator();
    }
}
