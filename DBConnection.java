import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
    public static void main (String[] args) {

        //args[0] user, args[1] pass, args[2] db-name
        String user = args[0]; 
        String pass = args[1];
        String url = "jdbc:mariadb://127.0.0.1:3306/" + args[2];
        // String user = "root";
        // String pass = "";
        // String url = "jdbc:mariadb://127.0.0.1:3306/labwork";

        ArrayList<Sale> sales = new ArrayList<>();

        String query = 
            "SELECT o.order_no, c.customer_name, c.city, s.name, o.purchase_amt, s.commission " +
            "FROM orders o JOIN customer c ON o.customer_id = c.customer_id " +
            "JOIN salesman s ON o.salesman_id = s.salesman_id ";

        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Successfully connected!");
            
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            //look at every order row returned
            while (rs.next()) {

               Sale s = new Sale(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getDouble(5),
                rs.getDouble(6)
               );

               sales.add(s);

            }

            //Test
            System.out.println(sales.get(0));
           
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

class Sale {
    int orderNumber;
    String customerName;
    String customerCity;
    String salesmanName;
    double amount;
    double commissionAmount;

    public Sale(int oN, String cN, String cC, String sN, double amt, double com) {
        orderNumber = oN;
        customerName = cN;
        customerCity = cC;
        salesmanName = sN;
        amount = amt;
        commissionAmount = amt * com;
    }

    public String toString() {
        return orderNumber + " - " + customerName;
    }

}