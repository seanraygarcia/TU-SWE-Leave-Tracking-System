package org.example;

import java.lang.reflect.Array;
import java.util.*;

public final class LeaveTracker {

    private static int requestIdCounter = 0;

    public static void main(String[] args) {

        // Capstone Part 1

        /*Employee emp1 = new Employee(601, "Rebecca", "HR");
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
        System.out.println("Employee: " + lr3.getEmployee().getName() + ", Remaining Leave Balance: " + emp2.getLeaveBalance());*/

        // Capstone Part 2

        Map<Integer, Employee> employees = new HashMap<>();
        employees.put(1, new Employee(1, "Rebecca", "HR"));
        employees.put(2, new Employee(2, "Mark", "IT"));
        employees.put(3, new Employee(3, "Alice", "Finance"));
        employees.put(4, new Employee(4, "John", "Marketing"));

        ArrayList<LeaveRequest> allRequests = new ArrayList<>();
        Queue<LeaveRequest> pendingRequests = new LinkedList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("---------------");
            System.out.println("Welcome to the HR Leave Management System \n");
            System.out.println("--- Main Menu ---");
            System.out.println("1. Create New Leave Request");
            System.out.println("2. Process a Pending Request");
            System.out.println("3. View All Request Histories");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("--- Create New Leave Request ---");
                    System.out.println("Select an employee:");

                    for (Map.Entry<Integer, Employee> entry : employees.entrySet()) {
                        System.out.println(entry.getKey() + ". " + entry.getValue().getName() + " (" + entry.getValue().getDepartment() + ")");
                    }
                    System.out.print("Enter employee number: ");
                    int empChoice = scanner.nextInt();

                    System.out.println("Select leave type:");
                    System.out.println("1. Sick Leave");
                    System.out.println("2. Vacation Leave");
                    System.out.println("3. Maternity Leave");
                    System.out.print("Enter leave type (1-3): ");
                    int leaveType = scanner.nextInt();


                    System.out.print("Enter start date (YYYY-MM-DD): ");
                    String startDate = scanner.next();
                    System.out.print("Enter end date (YYYY-MM-DD): ");
                    String endDate = scanner.next();

                    LeaveRequest leaveRequest = null;

                    if (leaveType == 1) {
                        System.out.print("Is a medical certificate provided? (true/false): ");
                        boolean medicalCertificate = scanner.nextBoolean();

                        leaveRequest = new SickLeaveRequest(++requestIdCounter, employees.get(empChoice), startDate, endDate, medicalCertificate);
                    } else if (leaveType == 2) {
                        leaveRequest = new VacationLeaveRequest(++requestIdCounter, employees.get(empChoice), startDate, endDate, false);
                    } else if (leaveType == 3) {
                        leaveRequest = new MaternityLeaveRequest(++requestIdCounter, employees.get(empChoice), startDate, endDate, "2025-12-25");
                    }

                    allRequests.add(leaveRequest);
                    pendingRequests.add(leaveRequest);

                    break;
                case 2:
                    System.out.println("--- Processing Next Pending Request ---");

                    LeaveRequest requestToProcess = pendingRequests.poll();
                    if (requestToProcess != null) {
                        System.out.println("Request ID: " + requestToProcess.getRequestId());
                        System.out.println("Employee: " + requestToProcess.getEmployee().getName());
                        System.out.println("Leave Type: " + requestToProcess.leaveType);
                        System.out.println("Dates: " + requestToProcess.startDate + " to " + requestToProcess.endDate);

                        requestToProcess.processRequest();

                        requestToProcess.printStatusHistory();
                    } else {
                        System.out.println("No pending requests to process.");
                    }

                    break;
                case 3:
                    System.out.println("--- Viewing All Request Histories ---");

                    for (LeaveRequest req : allRequests) {
                        System.out.println("Request ID: " + req.getRequestId() + ", Employee: " +
                                req.getEmployee().getName() + ", Leave Type: " + req.leaveType + ", Dates: " + req.startDate + " to " + req.endDate);
                        req.printStatusHistory();
                        System.out.println();
                    }

                    break;
                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }
}