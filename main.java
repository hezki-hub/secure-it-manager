import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean is_true = true;
        // initiation 
        AuthenticationService authService = new AuthenticationService();
        do{
        System.out.println("========================================");
        System.out.println("\tSECUREIT MANAGEMENT SYSTEM\t");
         System.out.println("========================================");
        System.out.println("1. Register\n" + "2. Login\n" + "3. Exit");
        System.out.print("Choose an option: ");
        int user_input =  input.nextInt();
        input.nextLine();
        switch (user_input) {
  // for regestaration 
            case 1:
                int id = authService.generateNewUserId();
                System.out.print("enter Your name: ");
                String username = input.nextLine();
                System.out.print("Enter password: ");
                String password = input.nextLine();
                System.out.println("Choose your role: ");
                System.out.println("1)Admin\n2)Technician\n3)Customer");
                String role = input.next();
                User newUser = null;
                if (role.equalsIgnoreCase("Admin")){
                   newUser=  new admin(id, username, password);
                }else if(role.equalsIgnoreCase("Technician")){
                     newUser = new Technician(id, username, password);
                }else if (role.equalsIgnoreCase("Customer")){
                    newUser =  new customer(id, username, password);
                }else{
                    System.out.println("invalid role");
                }

              if (newUser != null) {
                 authService.registerUser(newUser);
                }
                break;
            
        //
            case 2:
                System.out.println("\tLOGIN\t");
                System.out.println("Enter user name");
                String login_Username = input.nextLine();
                System.out.println("Enter Password");
                String login_Password = input.nextLine();

                if(authService.login(login_Username, login_Password)){
                    User loggedInUser = authService.getCurrentUser();
                    System.out.println("Welcome " + loggedInUser.getUserName());
                    System.out.println("Role: " + loggedInUser.getRole());

                }else{
                    System.out.println("Invalid username or password. Please try again");
                }
                break;

            case 3:
                is_true = false;
                break;
            default:
                System.err.println("invalid input");
                break;
        }
        }while(is_true);
        input.close();
    }
}
