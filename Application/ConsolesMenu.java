package Application;

import java.util.Arrays;
import java.util.List;
import java.sql.SQLException;
import java.util.Scanner;

import ConsolesDao.ConsoleDao;
import ConsolesEntity.Consoles;

public class ConsolesMenu {

    private ConsoleDao consoleDao = new ConsoleDao();
    private Scanner scanner = new Scanner(System.in);
    private List<String> choices = Arrays.asList(
            "Display All Consoles",
            "Display A Console", 
            "Create A Console", 
            "Update A Console", 
            "Delete A Console");

    public void start() {
        String selection = "";
        do {
            displayMenu();
            selection = scanner.nextLine();
            try {
                if (selection.equals("1")) {
                    displayConsoles();
                } else if (selection.equals("2")) {
                    displayOneConsole();
                } else if (selection.equals("3")) {
                    createConsole();
                } else if (selection.equals("4")) {
                    //updateConsole();
                } else if (selection.equals("5")) {
                    deleteConsole();
                } else {
                    System.out.println("Invalid selection. Please choose an appropriate choice from the menu.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("When ready, hit enter to continue.");
            scanner.nextLine();
        } while (!selection.equals("-1"));
    }

    private void displayMenu() {
        System.out.println("Choose an option from the menu. To exit program, enter -1.\n---------------------------------");
        for (int i = 0; i < choices.size(); i++) {
            System.out.println(i + 1 + ": " + choices.get(i));
        }
    }

    private void displayConsoles() throws SQLException {
        List<Consoles> consoles = consoleDao.getConsoles();
        for (Consoles console : consoles) {
            System.out.println("Console Name: " + console.getName());
            System.out.println("Controller ports available on console: " + console.getControllers());
            System.out.println("Year released: " + console.getRelease_year());
            System.out.println("Price upon release: $" + console.getRelease_price());
            System.out.println("This console could play games online: " + console.getOnline_capable());
        }
    }

    private void displayOneConsole() throws SQLException {
        System.out.println("Enter the name of the console you want to display: ");
        String name = scanner.nextLine();
        Consoles console = consoleDao.getConsoleByName(name);
        System.out.println("Console Name: " + console.getName());
        System.out.println("Controller ports available on console: " + console.getControllers());
        System.out.println("Year released: " + console.getRelease_year());
        System.out.println("Price upon release: $" + console.getRelease_price());
        System.out.println("This console could play games online: " + console.getOnline_capable());
    }

    private void createConsole() throws SQLException {
        System.out.print("Enter new console name:");
        String name = scanner.nextLine();
        System.out.print("Enter number of controller ports available:");
        int controllers = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the console's release year(YYYY):");
        int release_year = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the console's release price(XXXXX.YY Dollars.Cents:");
        double release_price = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter if the console could be played online (true or false):");
        Boolean online_capable = Boolean.parseBoolean(scanner.nextLine());
        consoleDao.createNewConsole(name, controllers, release_year, release_price, online_capable);
    }

    private void deleteConsole() throws SQLException {
        System.out.print("Enter name of console to delete:");
        String name = scanner.nextLine();
        consoleDao.deleteConsoleByName(name);
    }
}
