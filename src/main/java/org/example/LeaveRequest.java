package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public abstract class LeaveRequest implements Approvable {

    protected int requestId;
    protected Employee employee;
    protected String startDate;
    protected String endDate;
    protected String status;
    protected String leaveType;

    private final ArrayList<StatusChange> statusHistory = new ArrayList<>();

    public LeaveRequest() {

    }

    public LeaveRequest(int requestId, Employee employee, String startDate, String endDate) {
        this.requestId = requestId;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "Pending";
    }

    public LeaveRequest(int requestId, Employee employee, String startDate, String endDate, String leaveType) {
        this.requestId = requestId;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "Pending";
        this.leaveType = leaveType;

        statusHistory.add(new StatusChange(this.status, LocalDate.now().toString(), "ADMIN"));
    }

    public abstract int calculateLeaveDays();

    public boolean approve(String approverName) {
        this.status = "Approved";

        statusHistory.add(new StatusChange(this.status, LocalDate.now().toString(), approverName));

        System.out.println("Leave request for " + employee.getName() + " has been approved by " + approverName + ".");
        return true;
    }

    public boolean deny(String approverName, String reason) {
        this.status = "Denied";

        statusHistory.add(new StatusChange(this.status, LocalDate.now().toString(), approverName));

        System.out.println("Leave request for " + employee.getName() + " has been denied by " + approverName + ". Reason: " + reason);
        return false;
    }

    public void printStatusHistory() {
        System.out.println("--- Status History for Leave Request ID: " + requestId + " ---");
        for (StatusChange change : statusHistory) {
            System.out.println("-> Status: " + change.getNewStatus() + ", Changed On: " + change.getChangeDate() + ", Changed By: " + change.getChangedBy());
        }
    }

    public boolean processRequest() {
        System.out.println("Processing generic leave request...");
        return true;
    }

    public int getNumberOfDays() {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        return Math.toIntExact(ChronoUnit.DAYS.between(start, end) + 1);
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class StatusChange {

        private String newStatus;
        private String changeDate;
        private String changedBy;

        public StatusChange(String newStatus, String changeDate, String changedBy) {
            this.newStatus = newStatus;
            this.changeDate = changeDate;
            this.changedBy = changedBy;
        }

        public String getNewStatus() {
            return newStatus;
        }

        public void setNewStatus(String newStatus) {
            this.newStatus = newStatus;
        }

        public String getChangeDate() {
            return changeDate;
        }

        public void setChangeDate(String changeDate) {
            this.changeDate = changeDate;
        }

        public String getChangedBy() {
            return changedBy;
        }

        public void setChangedBy(String changedBy) {
            this.changedBy = changedBy;
        }
    }
}
