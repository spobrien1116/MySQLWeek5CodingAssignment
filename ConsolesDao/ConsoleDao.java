package ConsolesDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConsolesEntity.Consoles;

public class ConsoleDao {
    
    private Connection connection;
    private final String GET_CONSOLES_QUERY = "SELECT * FROM consoles";
    private final String GET_CONSOLE_BY_NAME_QUERY = "SELECT * FROM consoles WHERE name = ?";
    private final String CREATE_NEW_CONSOLE_QUERY = 
    "INSERT INTO consoles(name, controllers, release_year, release_price, online_capable) VALUES(?, ?, ?, ?, ?)";
    private final String UPDATE_CONSOLE_BY_NAME = "UPDATE consoles SET name = ? WHERE name = ?";
    private final String DELETE_CONSOLE_BY_NAME = "DELETE FROM consoles WHERE name = ?";

    public ConsoleDao() {
        connection = DBConnection.getConnection();
    }

    public List<Consoles> getConsoles() throws SQLException {
        ResultSet rs = connection.prepareStatement(GET_CONSOLES_QUERY).executeQuery();
        List<Consoles> consoles = new ArrayList<Consoles>();
        while (rs.next()) {
            consoles.add(populateConsoles(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4), rs.getInt(5)));
        }
        return consoles;
    }

    private Consoles populateConsoles(String name, int controllers, int release_year, double release_price, int online_capable) throws SQLException {
        return new Consoles(name, controllers, release_year, release_price, online_capable);
    }

    public Consoles getConsoleByName(String name) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(GET_CONSOLE_BY_NAME_QUERY);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return populateConsoles(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4), rs.getInt(5));
    }

    public void createNewConsole(String name, int controllers, int release_year, double release_price, int online_capable)  throws SQLException {
        PreparedStatement ps = connection.prepareStatement(CREATE_NEW_CONSOLE_QUERY);
        ps.setString(1, name);
        ps.setInt(2, controllers);
        ps.setInt(3, release_year);
        ps.setDouble(4, release_price);
        ps.setInt(5, online_capable);
        ps.executeUpdate();
    }

    public void updateConsoleByName(String newName, String oldName) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(UPDATE_CONSOLE_BY_NAME);
        ps.setString(1,newName);
        ps.setString(2,oldName);
        ps.executeUpdate();
    }

    public void deleteConsoleByName(String name) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(DELETE_CONSOLE_BY_NAME);
        ps.setString(1, name);
        ps.executeUpdate();
    }
}
