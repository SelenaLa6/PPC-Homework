import java.sql.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.*;

public class EmployeeConn {
    public static void main(String[] args) {

        //1) connecting to the database with jdbc

        // args[0] user, args[1] pass, args[2] db-name
        String user = args[0];
        String pass = args[1];
        String url = "jdbc:mariadb://127.0.0.1:3306/employee";

        //want id, name, and salary of each employee
        String query = "SELECT id, name, salary FROM employees";

        ArrayList<Employee> employeesList = new ArrayList<>();

        try { // try to connect

            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Successfully connected!");
            
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            // populate employeesList
            while(rs.next()) {

                int id = rs.getInt(1);
                String name = rs.getString(2);
                double salary = rs.getDouble(3);

                employeesList.add(new Employee(id, name, salary));

            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) { // catch exceptions
            e.printStackTrace();
        }


        //2) printing a list of all employees

        Consumer<Employee> printEmployee = (e) -> {
            System.out.println(e.toString());
        };

        employeesList.stream().forEach(printEmployee);
        System.out.println();

        //3) Filter employees w/ salary > 50,000

        Predicate<Employee> isHighEarner = (e) -> (e.getSalary() > 50000);

        List<Employee> highEarners = employeesList.stream()
            .filter(isHighEarner).collect(Collectors.toList());

        //4) Print highEarners

        highEarners.stream().forEach(printEmployee);
        System.out.println();

        //5) apply tax reduction to high earners

        Function<Employee, Employee> applyTax = (e) -> (
            new Employee(
                e.getId(),
                e.getName(),
                e.getSalary() * 0.85
            )
        );

        //6) format salary like $52000.30

        Function<Employee, String> formatSalary = (e) -> (
            String.format("$%.2f", e.getSalary())
        );

        //7) filter, tax, and format --> list

        highEarners = employeesList.stream()
            .filter(isHighEarner)
            .map(applyTax)
            .collect(Collectors.toList());

        //print the new highEarners

        highEarners.stream().forEach(printEmployee);
        System.out.println();

        //8) extra
        /*
            apply 10% tax to salaries <=50000
            apply 15% to salaries >50000
            show names and salaries neatly formatted
        */

        Consumer<Employee> printEmployeeClean = (e) -> {
            System.out.print(
                String.format("%20s | $%.2f%n", e.getName(), e.getSalary())
            );
        };

        Function<Employee, Employee> applyTaxLow = (e) -> (
            new Employee(
                e.getId(),
                e.getName(),
                e.getSalary() * 0.9   
            )
        );

        System.out.print(
            String.format("%20s | Adjusted Salary%n", "Name")
                + "-".repeat(21) + "+" + "-".repeat(21) + "\n"
        );

        employeesList.stream()
            .map((e) -> isHighEarner.test(e) ? applyTax.apply(e) : applyTaxLow.apply(e))
            .forEach(printEmployeeClean);

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

    public String toString() {
        return String.format("Employee{id=%d, name='%s', salary=%.2f}", id, name, salary);
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public double getSalary() { return salary; }

}
