package ConsolesDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DBConnection {
    
    private static Scanner scanner = new Scanner(System.in);
    private final static String URL = "jdbc:mysql://localhost:3306/consoles";
    private static Connection connection;
    private static DBConnection instance;

    private DBConnection(Connection connection) {
        this.connection = connection;
    }

    public static Connection getConnection() {
        if (instance == null) {
            String scannerUsername = "";
            String scannerPassword = "";
            System.out.print("Please enter your username:");
            scannerUsername = scanner.nextLine();
            System.out.print("Please enter your password:");
            scannerPassword = scanner.nextLine();
            try {
                connection = DriverManager.getConnection(URL, scannerUsername, scannerPassword);
                instance = new DBConnection(connection);
                System.out.println("Connection to database successful.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return DBConnection.connection;
    }
}
