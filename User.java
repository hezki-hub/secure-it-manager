public class User{
    private int id;
    private String username;
    private String password;
    private String role;
    public User(int id, String username, String password, String role){
        this.id = id;
        this.password= password;
        this.username = username;
        this.role = role;

    }
// Getter method 
    public int getId(){
        return this.id;
    }
     public String getUserName(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getRole(){
        return this.role;
    }
//Setter method 
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    
}