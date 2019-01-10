package project1;

import Models.User;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) 
    {
        
            DatabaseAccess dbAccess = new DatabaseAccess();
            System.out.println("Welcome!");
            System.out.println("You can now login");
            Scanner sc = new Scanner(System.in);
            LoginScreen lgScreen = new LoginScreen();
            User currentUser = null;
            
        try {
              currentUser = lgScreen.Login();
            
            // Menu logic
           
            boolean exit = false;
            while (!exit){
                
                ApplicationsRoleMenu rl = new ApplicationsRoleMenu();
                rl.UserRoleMenu(currentUser.RoleId);

                System.out.println("Plz enter action: ");
                int action = sc.nextInt();
                
                switch (action){
                    case 0:
                        exit = true;
                        break;
                    case 1:
                        dbAccess.GetUsers();
                        break;
                    case 2:
                        System.out.println("Choose Receiver: ");
                        dbAccess.GetUsers();
                        int Receiver = sc.nextInt();
                        sc.nextLine();  // else scanner throws exception!!!
                        System.out.println("Enter message: ");
                        String Data = sc.nextLine();
                        dbAccess.CreateMessage(Data, currentUser.Id, Receiver);
                        break;
                    case 3:
                        dbAccess.ViewMessages(currentUser.Id);
                        break;
                    case 4:
                        dbAccess.ViewMessages(currentUser.Id);
                        System.out.println("Select a Message to Update: ");
                        int msgid = sc.nextInt();
                        sc.nextLine();    // else scanner throws exception!!!    
                        System.out.println("Update message text: ");
                        String messageText = sc.nextLine();
                        dbAccess.UpdateMessage(msgid, messageText);
                        break;
                    case 5:
                        if (currentUser.RoleId == 2)
                            break;
                        System.out.print("Enter Id: ");
                        int userId = sc.nextInt();
                        System.out.print("Enter new Username: ");
                        String newUsername = sc.next();
                        System.out.print("Enter new Password: ");
                        String newPassword = sc.next();
                        System.out.print("Enter new Role: ");
                        int newRole = sc.nextInt();
                        dbAccess.UpdateUser(userId, newUsername, newPassword, newRole);
                        break;
                    case 6:
                        if (currentUser.RoleId == 2)
                            break;
                        System.out.print("Enter Id: ");
                        int uId = sc.nextInt();
                        dbAccess.DeleteUser(uId);
                        break;
                    case 7:
                        if (currentUser.RoleId == 2)
                            break;
                        System.out.print("Enter username for new User: ");
                        String newUserName = sc.next();
                        System.out.print("Enter password for new User: ");
                        String newPass = sc.next();
                        System.out.print("Assign Role for new User: ");
                        int newRoleid = sc.nextInt();
                       
                        dbAccess.CreateUser(newUserName, newPass, newRoleid);
                        break;
                    case 8:
                        if (currentUser.RoleId == 2)
                            break;
                        System.out.print("Enter message id: ");
                        int messageId = sc.nextInt();
                        dbAccess.DeleteMessage(messageId);
                        break;
                    default:
                        System.out.println("No such action available!");
                        break;
                }
            }
        } 
            catch (SQLException ex)
        {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}