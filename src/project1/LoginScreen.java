package project1;

import Models.User;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginScreen 
{
    
  public User Login() throws SQLException
    {
        Scanner sc = new Scanner(System.in);
        DatabaseAccess dbAccess = new DatabaseAccess();

        boolean isLogged = false; //dbAccess.Login(username, password);
        User currentUser = null;

        // Login logic
        while (!isLogged)
        {
            System.out.print("Enter Username: ");
            String username = sc.next();
            System.out.print("Enter Password: ");
            String password = sc.next();            
            currentUser = dbAccess.Login(username, password);

            if(currentUser == null)
            {
                System.out.println("Please Enter your credentials again");
            }
            else
            {
                isLogged = true; 
            }   
        }
        System.out.println("Logged in");
        return currentUser;
    }
}
