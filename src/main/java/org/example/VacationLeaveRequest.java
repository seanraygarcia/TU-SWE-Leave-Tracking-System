package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class VacationLeaveRequest extends LeaveRequest {

    private boolean isPaidTimeOff;

    public VacationLeaveRequest(int requestId, Employee employee, String startDate, String endDate, boolean isPaidTimeOff) {
        super(requestId, employee, startDate, endDate, "Vacation Leave");
        this.isPaidTimeOff = isPaidTimeOff;

        System.out.println("Successfully created vacation leave request for " + employee.getName() + " from " + startDate + " to " + endDate + ".");
    }

    @Override
    public int calculateLeaveDays() {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        return Math.toIntExact(ChronoUnit.DAYS.between(start, end) + 1);
    }

    @Override
    public boolean processRequest() {

        System.out.println("Processing vacation leave request for " + employee.getName() + "...");

        employee.updateLeaveBalance(calculateLeaveDays());

        return approve("HR");
    }

    public boolean isPaidTimeOff() {
        return isPaidTimeOff;
    }

    public void setPaidTimeOff(boolean paidTimeOff) {
        isPaidTimeOff = paidTimeOff;
    }
}
