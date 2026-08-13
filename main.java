import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean is_true = true;
        // initiation 
        AuthenticationService authService = new AuthenticationService();
        CustomerService customerService = new CustomerService();
        DeviceService deviceService = new DeviceService();
        TicketService ticketService = new TicketService();
        do{
        System.out.println("========================================");
        System.out.println("\tSECUREIT MANAGEMENT SYSTEM\t");
         System.out.println("========================================");
        System.out.println("1. Register\n" + "2. Login\n" + "3. Exit");
        
        int user_input =  getValidIntInput(input, "Choose an option: ");
        
        switch (user_input) {
  // for regestaration 
            case 1:
                int id = authService.generateNewUserId();
                System.out.print("enter Your name: ");
                String username = input.nextLine();
                System.out.print("Enter password: ");
                String password = input.nextLine();
                System.out.println("Enter Email: ");
                String email = input.nextLine();
                System.out.println("Enter Phone Number: ");
                String phone = input.nextLine();
                System.out.println("Enter Company name: ");
                String companyName = input.nextLine();
                System.out.println("Choose your role: ");
                System.out.println("1)Admin\n2)Technician\n3)Customer");
                String role = input.nextLine();
                User newUser = null;
                if (role.equalsIgnoreCase("Admin")){
                   newUser=  new Admin(id, username, password);
                }else if(role.equalsIgnoreCase("Technician")){
                     newUser = new Technician(id, username, password);
                }else if (role.equalsIgnoreCase("Customer")){
                    newUser =  new Customer(id, username, password, email, phone, companyName);
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
                    if (loggedInUser.getRole().equalsIgnoreCase("Admin")){
                        boolean adminRunning = true;
                        while(adminRunning){
                            System.out.println("\n=== ADMIN DASHBOARD ===");
System.out.println("1. Manage Customers");
System.out.println("2. Manage Devices");
System.out.println("3. Manage Tickets");
System.out.println("4. Logout");
System.out.print("Choose an option: ");


int adminChoice = getValidIntInput(input, "Choose an option: ");
 
switch (adminChoice) {
    case 1:
        System.out.println("\n--- MANAGE CUSTOMERS ---");
        System.out.println("1. Add Customer");
        System.out.println("2. View All Customers");
        System.out.println("3. Delete Customer");
        int customerChoice = getValidIntInput(input, "Choose an option: ");
        

        if (customerChoice == 1) {
            System.out.print("Enter Username: ");
            String cUsername = input.nextLine();
            System.out.print("Enter Password: ");
            String cPassword = input.nextLine();
            System.out.print("Enter Email: ");
            String cEmail = input.nextLine();
            System.out.print("Enter Phone: ");
            String cPhone = input.nextLine();
            System.out.print("Enter Company Name: ");
            String cCompany = input.nextLine();

            customerService.addCustomer(cUsername, cPassword, cEmail, cPhone, cCompany);
        } else if (customerChoice == 2) {
           customerService.getAllCustomers();
        } else if (customerChoice == 3) {
        
            int deleteId = getValidIntInput(input, "Enter Customer ID to delete: ");
            
            
            if (customerService.deleteCustomer(deleteId)) {
                System.out.println("Customer deleted successfully!");
            } else {
                System.out.println("Customer ID not found.");
            }
        }
        break;

    case 2:
        System.out.println("\n--- MANAGE DEVICES ---");
        System.out.println("1. Add Device");
        System.out.println("2. View Devices by Customer ID");
        System.out.println("3. Update Device Status");
       
        int deviceChoice = getValidIntInput(input, "Choose an option: ");
        

        if (deviceChoice == 1) {
          
            int devCustId = getValidIntInput(input, "Enter Customer ID: ");
            
            System.out.print("Enter Device Type (e.g. Laptop, Server): ");
            String type = input.nextLine();
            System.out.print("Enter Brand: ");
            String brand = input.nextLine();
            System.out.print("Enter Serial Number: ");
            String serial = input.nextLine();

            deviceService.addDevice(devCustId, type, brand, serial);
        } else if (deviceChoice == 2) {
            
            int searchCustId = getValidIntInput(input, "Enter Customer ID:");
            
            System.out.println(deviceService.getDevicesByCustomerId(searchCustId));
        } else if (deviceChoice == 3) {
      
            int devId = getValidIntInput(input, "Enter Device ID: ");
            
           
            int statusOpt = getValidIntInput(input, "Select Status: 1) ACTIVE  2) UNDER_MAINTENANCE  3) DECOMMISSIONED:");
            

            DeviceStatus newStatus = DeviceStatus.ACTIVE;
            if (statusOpt == 2) newStatus = DeviceStatus.UNDER_MAINTENANCE;
            if (statusOpt == 3) newStatus = DeviceStatus.DECOMMISSIONED;

            if (deviceService.updateDeviceStatus(devId, newStatus)) {
                System.out.println("Device status updated!");
            } else {
                System.out.println("Device ID not found.");
            }
        }
        break;

    case 3:
        System.out.println("\n--- MANAGE TICKETS ---");
        System.out.println("1. Create Ticket");
        System.out.println("2. View Tickets by Customer ID");
        System.out.println("3. Update Ticket Status");
        
        int ticketChoice = getValidIntInput(input,"Choose an option");
        

        if (ticketChoice == 1) {
            
            int tCustId = getValidIntInput(input, "Enter Customer ID: ");
            
            
            int tDevId = getValidIntInput(input, "Enter Device ID: ");
            
            System.out.print("Enter Issue Description: ");
            String desc = input.nextLine();

            ticketService.createTicket(tCustId, tDevId, desc);
        } else if (ticketChoice == 2) {
            
            int searchCustId = getValidIntInput(input, "Enter Customer ID: ");
            
            System.out.println(ticketService.getTicketsByCustomerId(searchCustId));
        } else if (ticketChoice == 3) {
            
            int tId = getValidIntInput(input, "Enter Ticket ID");
           
            
            int tStatusOpt = getValidIntInput(input, "Select Status: 1) OPEN  2) IN_PROGRESS  3) RESOLVED  4) CLOSED");
            

            TicketStatus newTStatus = TicketStatus.OPEN;
            if (tStatusOpt == 2) newTStatus = TicketStatus.IN_PROGRESS;
            if (tStatusOpt == 3) newTStatus = TicketStatus.RESOLVED;
            if (tStatusOpt == 4) newTStatus = TicketStatus.CLOSED;

            if (ticketService.updateTicketStatus(tId, newTStatus)) {
                System.out.println("Ticket status updated!");
            } else {
                System.out.println("Ticket ID not found.");
            }
        }
        break;

    case 4:
        System.out.println("Logging out of Admin Dashboard...");
        adminRunning = false; // Exits the admin while loop
        break;

    default:
        System.out.println("Invalid choice. Please pick between 1 and 4.");
        break;
}
                        }
                    }else if (loggedInUser.getRole().equalsIgnoreCase("Customer")){
                        boolean customerRunning = true;
                        while(customerRunning){
                            System.out.println("\n=== CUSTOMER DASHBOARD ===");
                            System.out.println("1. View My Devices");
                            System.out.println("2. View My Support Tickets");
                            System.out.println("3. Submit a New Support Ticket");
                            System.out.println("4. Logout");
                            
                            int custChoice = getValidIntInput(input, "Choose an option: ");
                            
                            switch (custChoice) {
            case 1:
                System.out.println("\n--- MY DEVICES ---");
                System.out.println(deviceService.getDevicesByCustomerId(loggedInUser.getId()));
                break;
            case 2:
                System.out.println("----- MY SUPPORT TICKET-----");
                System.out.println(ticketService.getTicketsByCustomerId(loggedInUser.getId()));
                break;
            case 3:
                System.out.println("----Submit Support Ticket-----");
                
                int devId = getValidIntInput(input, "Enter Device ID: ");
                
                System.out.print("Describe the issue: ");
                String description = input.nextLine();

                ticketService.createTicket(loggedInUser.getId(), devId, description);
                break;

            case 4:
                System.out.println("Logging out...");
                customerRunning = false;
                break;
            default:
                System.out.println("Invalid option. Please choose between 1 and 4.");
                break;
                        }
                    }



                }else if (loggedInUser.getRole().equalsIgnoreCase("Technician")){

                    boolean techRunning = true;
                    while(techRunning){
                        System.out.println("\n=== TECHNICIAN DASHBOARD ===");
        System.out.println("1. View All Tickets");
        System.out.println("2. Update Ticket Status");
        System.out.println("3. Logout");
        int techInput = getValidIntInput(input, "Choose an option");

        switch (techInput) {
            case 1:
                System.out.println("\n--- ALL SYSTEM TICKETS ---");
            ticketService.getAllTickets();
                break;
            case 2:
                System.out.println("\n--- UPDATE TICKET STATUS ---");
                int tId = getValidIntInput(input, "Enter Ticket ID: ");
                int statusOpt = getValidIntInput(input, "Select Status: 1) OPEN  2) IN_PROGRESS  3) RESOLVED  4) CLOSED: ");
            TicketStatus newStatus = TicketStatus.OPEN;
            if(statusOpt == 2) newStatus = TicketStatus.IN_PROGRESS;
            if(statusOpt == 3) newStatus = TicketStatus.RESOLVED;
            if(statusOpt == 4) newStatus = TicketStatus.CLOSED;
            if (ticketService.updateTicketStatus(tId, newStatus)) {
                    System.out.println("Ticket status updated successfully!");
                } else {
                    System.out.println("Ticket ID not found.");
                }
                break;
            case 3:
                System.out.println("Logging out!!");
                techRunning = false;
                break;
            default:
                System.out.println("invalid input!!");
                break;
        }
       
                    }
                }else{
                    System.out.println("Invalid username or password. Please try again");
                }

                break;}

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


  // Simple way to catch error (by making a method for it alone!!)
    public static int getValidIntInput(Scanner scanner, String prompt) {
    while (true) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number.");
        }
    }
}
}
