import java.util.ArrayList;
/*
This service will manage user credentials in memory and
 handle authentication logic
  (logging in, registering, and tracking who is currently logged in). 
  */
public class AuthenticationService {

    private ArrayList<User> users; //will store User objects
    private User currentUser; //holds the specific User object
    private int nextUserId = 1;
    public AuthenticationService() {
    this.users = new ArrayList<>();
    this.currentUser = null; 
     
}
   
    public int generateNewUserId(){
        return nextUserId++;
    }

     public void registerUser(User user){
        for(int i = 0; i< users.size(); i++){
             if(user.getUserName().equals(users.get(i).getUserName())){
            System.out.println("user already exist");
            return;
        }
    }
    users.add(user);

        }
    public boolean login(String username, String password){
        for(int i = 0; i< users.size(); i++){
            if(username.equals(users.get(i).getUserName()) && password.equals(users.get(i).getPassword())){
               currentUser = users.get(i);
                return true;
                
            }
        }
    return false;
    }

    public void logout(){
        currentUser = null;
    }
    public  User getCurrentUser(){
        return currentUser;
    }
    public ArrayList<User> getUsers() {
    return this.users;
}
}
