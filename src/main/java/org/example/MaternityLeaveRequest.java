package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MaternityLeaveRequest extends LeaveRequest {

    private String expectedDeliveryDate;

    public MaternityLeaveRequest(int requestId, Employee employee, String startDate, String endDate, String expectedDeliveryDate) {
        super(requestId, employee, startDate, endDate, "Maternity Leave");
        this.expectedDeliveryDate = expectedDeliveryDate;

        System.out.println("Successfully created maternity leave request for " + employee.getName() + " from " + startDate + " to " + endDate + ".");
    }

    @Override
    public int calculateLeaveDays() {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        return Math.toIntExact(ChronoUnit.DAYS.between(start, end) + 1);
    }

    @Override
    public boolean processRequest() {

        System.out.println("Processing maternity leave request for " + employee.getName() + "...");

        employee.updateLeaveBalance(calculateLeaveDays());

        return approve("HR");
    }

    public String getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(String expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }
}
