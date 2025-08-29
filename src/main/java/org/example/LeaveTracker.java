package org.example;

public final class LeaveTracker {
    public static void main(String[] args) {

        Employee emp1 = new Employee(601, "Rebecca", "HR");
        Employee emp2 = new Employee(602, "Mark", "IT");

        LeaveRequest lr1 = new LeaveRequest(101, emp1, "2024-07-01", "2024-07-05");
        LeaveRequest lr2 = new LeaveRequest(102, emp1, "2024-08-10", "2024-08-12");
        LeaveRequest lr3 = new LeaveRequest(103, emp2, "2024-09-15", "2024-09-20");

        System.out.println("Employee: " + emp1.getName() + "\nLeave Balance: " + emp1.getLeaveBalance());

        System.out.println("------------------------------");
        emp1.requestLeave(lr1);
        System.out.println("Employee: " + lr1.getEmployee().getName() + ", Remaining Leave Balance: " + emp1.getLeaveBalance());

        System.out.println("------------------------------");
        emp1.requestLeave(lr2);
        System.out.println("Employee: " + lr2.getEmployee().getName() + ", Remaining Leave Balance: " + emp1.getLeaveBalance());

        System.out.println("------------------------------");
        emp2.requestLeave(lr3);
        System.out.println("Employee: " + lr3.getEmployee().getName() + ", Remaining Leave Balance: " + emp2.getLeaveBalance());
    }
}