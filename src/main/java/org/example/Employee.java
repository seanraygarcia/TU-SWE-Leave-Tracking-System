package org.example;

public class Employee {

    private int employeeId;
    private String name;
    private String department;
    private int leaveBalance;

    // Default constructor
    public Employee() {
        this.employeeId = 0;
        this.name = "Unknown";
        this.leaveBalance = 15;
    }

    // Parameterized constructor
    public Employee(int employeeId, String name, String department) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.leaveBalance = 20;
    }

    public void updateLeaveBalance(int days) {
        leaveBalance -= days;
    }

    public boolean requestLeave(int numberOfDays) {

        System.out.println("Requesting " + numberOfDays + " days of leave for " + name + "...");

        if (numberOfDays <= leaveBalance) {
            leaveBalance -= numberOfDays;
            return true;
        } else {
            System.out.println("Insufficient leave balance.");
            return false;
        }
    }

    public void requestLeave(LeaveRequest leaveRequest) {
        int numberOfDays = leaveRequest.getNumberOfDays();
        System.out.println("Requesting " + numberOfDays + " days of leave for " + name
        + " from " + leaveRequest.getStartDate() + " to " + leaveRequest.getEndDate() + ".");

        if (numberOfDays <= leaveBalance) {
            leaveBalance -= numberOfDays;
            leaveRequest.setStatus("Approved");
            System.out.println("Leave approved.");
        } else {
            leaveRequest.setStatus("Denied");
            System.out.println("Insufficient leave balance. Leave denied.");
        }
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(int leaveBalance) {

        if (leaveBalance < 0) {
            System.out.println("Leave balance cannot be negative.");
        } else {
            this.leaveBalance = leaveBalance;
        }
    }
}
