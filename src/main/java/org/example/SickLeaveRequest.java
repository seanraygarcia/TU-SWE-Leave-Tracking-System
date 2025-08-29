package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SickLeaveRequest extends LeaveRequest {

    private boolean medicalCertificateProvided;

    public SickLeaveRequest(int requestId, Employee employee, String startDate, String endDate, boolean medicalCertificateProvided) {
        super(requestId, employee, startDate, endDate, "Sick Leave");
        this.medicalCertificateProvided = medicalCertificateProvided;

        System.out.println("Successfully created sick leave request for " + employee.getName() + " from " + startDate + " to " + endDate + ".");
    }

    @Override
    public int calculateLeaveDays() {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        return Math.toIntExact(ChronoUnit.DAYS.between(start, end) + 1);
    }

    @Override
    public boolean processRequest() {

        System.out.println("Processing sick leave request for " + employee.getName() + "...");

        int numberOfLeaveDays = calculateLeaveDays();

        if (numberOfLeaveDays > 2 && !medicalCertificateProvided) {
            return deny("HR", "Medical certificate required for sick leave longer than 2 days.");
        }

        if (numberOfLeaveDays > employee.getLeaveBalance()) {
            return deny("HR", "Insufficient leave balance.");
        }

        employee.updateLeaveBalance(numberOfLeaveDays);

        return approve("HR");
    }

    public boolean isMedicalCertificateProvided() {
        return medicalCertificateProvided;
    }

    public void setMedicalCertificateProvided(boolean medicalCertificateProvided) {
        this.medicalCertificateProvided = medicalCertificateProvided;
    }
}
