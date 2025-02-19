import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManagementSystem {
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Search Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    updateEmployee();
                    break;
                case 3:
                    removeEmployee();
                    break;
                case 4:
                    searchEmployee();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addEmployee() {
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter employee salary: ");
        double salary = scanner.nextDouble();

        Employee employee = new Employee(id, name, salary);
        employees.add(employee);
        System.out.println("Employee added successfully!");
    }

    private static void updateEmployee() {
        System.out.print("Enter employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Employee emp : employees) {
            if (emp.getId() == id) {
                System.out.print("Enter new name: ");
                String name = scanner.nextLine();
                System.out.print("Enter new salary: ");
                double salary = scanner.nextDouble();

                emp.setName(name);
                emp.setSalary(salary);
                System.out.println("Employee details updated successfully!");
                return;
            }
        }
        System.out.println("Employee not found!");
    }

    private static void removeEmployee() {
        System.out.print("Enter employee ID to remove: ");
        int id = scanner.nextInt();

        if (employees.removeIf(emp -> emp.getId() == id)) {
            System.out.println("Employee removed successfully!");
        } else {
            System.out.println("Employee not found!");
        }
    }

    private static void searchEmployee() {
        System.out.print("Enter employee ID to search: ");
        int id = scanner.nextInt();

        for (Employee emp : employees) {
            if (emp.getId() == id) {
                System.out.println("Employee found:");
                System.out.println("ID: " + emp.getId());
                System.out.println("Name: " + emp.getName());
                System.out.println("Salary: " + emp.getSalary());
                return;
            }
        }
        System.out.println("Employee not found!");
    }
}

class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    
    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }

    
    public void setName(String name) { this.name = name; }
    public void setSalary(double salary) { this.salary = salary; }
}
