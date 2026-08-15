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
    public String toCSV() {
    return id + "," + username + "," + role + ", " + password;
}
 public static User fromCSV(String csvLine){
    
    String[] data = csvLine.split(",");
    int id = Integer.parseInt(data[0]);
    String username  = (data[1]);
    String password = data[2];
    String role = data[3];
    
    
    return new User(id, username, password, role);
 }

}