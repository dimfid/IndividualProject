package project1;

import Models.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class DatabaseAccess {
    private String dbURL = "jdbc:mysql://localhost:3306/project1_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String username = "root";
    private String password = ";;212121";
    private Connection conn = null;
    FilesAccess f = new FilesAccess();
    
    
    public DatabaseAccess()
    {
        try 
        {
            conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null) 
            {
                System.out.println("Connected");
            }
        } catch (SQLException ex) 
            {
                ex.printStackTrace();
            }
    }
        
    //CRUD for Users
    public void CreateUser(String username, String password, int roleId) throws SQLException
    {
        if(roleId != 1 && roleId !=2)
        {
            System.out.println("Failed to create user because ther is no role with id: " + roleId + "!");
            return;
        }
        
        String sql = "INSERT INTO User (Username, Password, Role_Id) VALUES (?, ?, ?)";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        statement.setInt(3, roleId);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) 
        {
            System.out.println("A new user with username: " + username + " was inserted successfully!");
        }
    }
    
    public void UpdateUser(int id, String username, String password, int roleId) throws SQLException
    {
        String sql = "UPDATE User SET Username=?, Password=?, Role_Id=? WHERE Id=?";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        statement.setInt(3, roleId);
        statement.setInt(4, id);
        
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) 
        {
            System.out.println("The User with Id: " + id + " was updated successfuly");
        }
    }
    
    public void DeleteUser(int id) throws SQLException
    {
        String sql = "DELETE FROM User WHERE Id=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) 
        {
            System.out.println("The user with Id: " + id + " was deleted successfully!");
        }
    }
    
    public void GetUsers() throws SQLException
    {
        String sql = "SELECT * FROM User";

        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next())
        {
            int Id = result.getInt(1);
            String Username = result.getString(2);
            String Password = result.getString(3);
            int Role_Id = result.getInt(4);

            String output = "Id: %d - Username: %s - Password: %s - RoleId: %d";
            System.out.println(String.format(output, Id, Username, Password, Role_Id));
        }
    }
            //Login method
    public User Login(String Username, String Password) throws SQLException
    {
        String sql = String.format("SELECT * FROM User WHERE Username=\"%s\" AND Password=\"%s\"", Username, Password);

        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        
        if(result.first())
        {
            User u = new User();
            u.Id = Integer.parseInt(result.getString(1));
            u.Username = result.getString(2);
            u.Password = result.getString(3);
            u.RoleId = Integer.parseInt(result.getString(4));
            
            return u;
        }
        return null;
    }
    
            //Timestamp method for use in message creation
    private static java.sql.Timestamp getCurrentTimeStamp() 
    {
	java.util.Date today = new java.util.Date();
	return new java.sql.Timestamp(today.getTime());
    }

        //CRUD for Messages
    public void CreateMessage(String Data, int Sender, int Receiver) throws SQLException
    {
        String sql = "INSERT INTO Message (data, date, sender, receiver) VALUES (?, ?, ?, ?)";
 
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, Data);
        statement.setTimestamp (2, getCurrentTimeStamp());
        statement.setInt(3, Sender);
        statement.setInt(4, Receiver);
        
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) 
        {
            System.out.println("A new Message was sent to User: " + Receiver + " ! ");
            f.writer("create_message", "sender is: "+Sender+" || receiver is: "+Receiver+" || message:"+Data+"");
        }
    }
    
    public void ViewMessages(int userId) throws SQLException
    {
        String sql = String.format("SELECT * FROM Message WHERE Sender = %d OR Receiver = %d", userId, userId);

        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        
        while (result.next())
        {
            int Id = result.getInt(1);
            String Data = result.getString(2);
            Timestamp Date = result.getTimestamp(3);
            int Sender = result.getInt(4);
            int Receiver = result.getInt(5);

            String output = "MessageId: %d - Sender: %d - Receiver: %d - Date: %s - Message: %s";
            System.out.println(String.format(output, Id, Sender, Receiver, Date, Data));
        }
    }
    
    public void UpdateMessage(int messageId, String newText) throws SQLException
    {
        String sql = "UPDATE Message SET Data =? WHERE Id=?";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, newText);
        statement.setInt(2, messageId);
        
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) 
        {
            System.out.println("The message with Id: " + messageId + " was updated successfuly");
            f.writer("update_message", "message_Id :"+messageId+", message:"+newText+"");
        }
    }
    

    public void DeleteMessage(int messageId) throws SQLException
    {
        String sql = "DELETE FROM Message WHERE Id=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, messageId);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) 
        {
            System.out.println("The message with Id: " + messageId + " was deleted successfully!");
            f.writer("delete_message", "deleted mesagge is: "+messageId+"");
        }
    }
}


