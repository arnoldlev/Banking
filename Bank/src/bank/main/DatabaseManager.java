/**
 * @author Arnold Lev
 * A manager class that has direct access to database
 */
package bank.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import bank.accounts.Account;

public class DatabaseManager {
    
	// My web host MySQL Database
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://144.217.66.186:3306/arnoldle_bank";
    private static final String USERNAME = "arnoldle";
    private static final String PASSWORD = "u[r@4OY7bYl44W";
    private Connection connection;
    private Properties properties;
    
    /**
     * @author Arnold Lev
     * @param int ID
     * Example of deleting data from database
     * */
    public void deleteAccount(int ID) {
    	try {
    		Connection con = connect();
    		PreparedStatement stat = con.prepareStatement("DELETE FROM courses WHERE courseID = ?");
    		stat.setInt(1, ID);
    		stat.execute();
    		
    		stat.close();
    		con.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		disconnect();
    	}
    }
    
    /**
     * @author Arnold Lev
     * @param String or Object
     * Example of inserting data into a table
     * */
    public void addAccount(String c) {
    	try {
    		Connection con = connect();
    		PreparedStatement stat = con.prepareStatement("INSERT INTO courses(courseID, name, prof, units) VALUES (?,?,?,?)");
    		stat.setInt(1, 1);
    		stat.setString(2, c);
    		stat.setString(3, c);
    		stat.setDouble(4, 1.0);
	    	stat.execute();
	    	
    		stat.close();

    		con.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		disconnect();
    	}
    }
    
    /**
     * @author Arnold Lev
     * @param None
     * Example of retrieving data from a database
     * */
    public ArrayList<Account> loadCourses() {
    	ArrayList<Account> acc = new ArrayList<Account>();
    	try {
    		Connection con = connect();
    		PreparedStatement stat = con.prepareStatement("SELECT * FROM accounts");
    		ResultSet rs = stat.executeQuery();
    		while (rs.next()) {
    			// Example: Account c = new Account(rs.getInt("courseID"), rs.getString("name"), rs.getString("prof"), rs.getDouble("units"));
    			// Example: acc.add(c);
    			
    		}
    		rs.close();
    		stat.close();
    		con.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		disconnect();
    	}
    	return acc;
    }

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
        }
        return properties;
    }

    private Connection connect() {
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, getProperties());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    private void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}