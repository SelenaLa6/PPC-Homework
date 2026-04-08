import java.sql.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.*;

public class SaleInfo {
    public static void main (String[] args) {

        //args[0] user, args[1] pass, args[2] db-name
        String user = args[0]; 
        String pass = args[1];
        String url = "jdbc:mariadb://127.0.0.1:3306/" + args[2];
        // String user = "root";
        // String pass = "";
        // String url = "jdbc:mariadb://127.0.0.1:3306/labwork";

        ArrayList<SalesPerson> salesPeople = new ArrayList<>();

        String query = 
            "SELECT s.name, s.city, s.commission, SUM(o.purchase_amt) " +
            "FROM salesman s LEFT JOIN orders o ON s.salesman_id = o.salesman_id " +
            "GROUP BY s.salesman_id";

        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Successfully connected!");
            
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            //each row -> new SalesPerson object in salesPeople
            while (rs.next()) {

               String name = rs.getString(1);
               String city = rs.getString(2);
               double comm = rs.getDouble(3);
               int sales = rs.getInt(4);

               salesPeople.add(new SalesPerson(name, city, comm, sales));

            }

            rs.close();
            stmt.close();
            conn.close();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Consumer<Map.Entry<String, Double>> printNameMoney = (s) -> {
            System.out.print(
              String.format("%25s | $%.2f%n",
                s.getKey(), s.getValue()
              )  
            );
        };
        
        Function<SalesPerson, Double> totalCommissions = (s) -> (s.totalSales*s.commission);
        Function<SalesPerson, Double> totalEarnings = (s) -> (s.totalSales + totalCommissions.apply(s));

        //1. Print all salesPersons names and their total earnings in a neatly formatted table.
        //total earnings = totalSales + (totalSales*commission)

        System.out.print(
            String.format("%25s | %s%n", 
            "Salesperson", "Total Earnings") +
            "-".repeat(26) + "+" + "-".repeat(21) + "\n"
        );

        salesPeople.stream().collect(
            Collectors.toMap(s->s.name, 
                totalEarnings, 
                (s1,s2)->s1, 
                HashMap::new)
        ).entrySet().stream().forEach(printNameMoney);

        System.out.println();

        //2. Print all salesPersons names and their total commissions in a neatly formatted table.

        System.out.print(
            String.format("%25s | %s%n", 
            "Salesperson", "Total Commissions") +
            "-".repeat(26) + "+" + "-".repeat(21) + "\n"
        );

        salesPeople.stream().collect(
            Collectors.toMap(s->s.name, 
                totalCommissions, 
                (s1,s2)->s1, 
                HashMap::new)
        ).entrySet().stream().forEach(printNameMoney);

    }
}