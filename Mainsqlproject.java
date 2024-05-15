package sqlconnectivety;

import java.sql.SQLException;
import java.util.Scanner;

public class Mainsqlproject {
	public static void main(String[] args) throws SQLException {
	    EmployeeDB employeeDB = new EmployeeDB();
	    Scanner scanner = new Scanner(System.in);
	    int choice;

	    do {
	        System.out.println("\nEmployee Management System");
	        System.out.println("1. Add Employee");
	        System.out.println("2. Get Employee Details");
	        System.out.println("3. Update Employee");
	        System.out.println("4. Delete Employee");
	        System.out.println("5. Exit");
	        System.out.print("Enter your choice: ");

	        choice = scanner.nextInt();

	        switch (choice) {
	            case 1:
	                System.out.print("Enter Employee ID: ");
	                int id = scanner.nextInt();
	                System.out.print("Enter Employee Name: ");
	                String name = scanner.nextLine();
	                System.out.print("Enter Employee Position: ");
	                String position = scanner.nextLine();
	                employeeDB.addEmployee(id, name, position);
	                break;
	            case 2:
	                System.out.print("Enter Employee ID to retrieve details: ");
	                id = scanner.nextInt();
	                employeeDB.getEmployee(id);
	                break;
	            case 3:
	                System.out.print("Enter Employee ID to update: ");
	                id = scanner.nextInt();
	                System.out.print("Enter new Employee Name (or leave blank to keep existing): ");
	                name = scanner.nextLine();
	                scanner.nextLine(); // Consume extra newline character
	                System.out.print("Enter new Employee Position (or leave blank to keep existing): ");
	                position = scanner.nextLine();
	                employeeDB.updateEmployee(id, name.isEmpty() ? null : name, position.isEmpty() ? null : position);
	                break;
	            case 4:
	                System.out.print("Enter Employee ID to delete: ");
	                id = scanner.nextInt();
	                employeeDB.deleteEmployee(id);
	                break;
	            case 5:
	                System.out.println("Exiting...");
	                break;
	            default:
	                System.out.println("Invalid Choice!");
	        }
	    } while (choice != 5);

	    scanner.close();
	}

}
