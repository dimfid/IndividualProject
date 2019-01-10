package project1;

import java.util.HashMap;
import java.util.Set;

public class ApplicationsRoleMenu { 

    public void UserRoleMenu(int roleId){
        
        HashMap AdminMenu = new HashMap<Integer, String>(){
            {        
                put(0,"Exit");
                put(1,"View All Users");
                put(2, "Create Message");
                put(3, "View Messages");
                put(4, "Update Message");
                put(5,"Update User");
                put(6,"Delete User");
                put(7,"Create User");
                put(8,"Delete Message");
            }
        };
    
        HashMap SuperUserMenu = new HashMap<Integer, String>(){
            {
                put(0,"Exit");
                put(1,"View All Users");
                put(2, "Create Message");
                put(3, "View Messages");
                put(4, "Update Message");
            }
        };
        
        HashMap UserMenu = new HashMap<Integer, String>(){
            {
                put(0,"Exit");
                put(1,"View All Users");
                put(2, "Create Message");
                put(3, "View Messages");
            }
        };
        
        if (roleId == 1){           
            Set<Integer> keys = AdminMenu.keySet();
            for(Integer key: keys)
                System.out.println(key + " - " + AdminMenu.get(key));
            
        }else if(roleId == 2){
            Set<Integer> keys = SuperUserMenu.keySet();
            for(Integer key: keys)
                System.out.println(key + " - " + SuperUserMenu.get(key));

        }else if(roleId == 3){
            Set<Integer> keys = UserMenu.keySet();
            for(Integer key: keys)
                System.out.println(key + " - " + UserMenu.get(key));
        
        }else{
            System.out.println("Invalid role ID");
        }
    }
}
