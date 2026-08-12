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


int adminChoice = input.nextInt();
input.nextLine(); 
switch (adminChoice) {
    case 1:
        System.out.println("\n--- MANAGE CUSTOMERS ---");
        System.out.println("1. Add Customer");
        System.out.println("2. View All Customers");
        System.out.println("3. Delete Customer");
        System.out.print("Choose an option: ");
        int customerChoice = input.nextInt();
        input.nextLine();

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
            System.out.println(customerService.getAllCustomers());
        } else if (customerChoice == 3) {
            System.out.print("Enter Customer ID to delete: ");
            int deleteId = input.nextInt();
            input.nextLine();
            
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
        System.out.print("Choose an option: ");
        int deviceChoice = input.nextInt();
        input.nextLine();

        if (deviceChoice == 1) {
            System.out.print("Enter Customer ID: ");
            int devCustId = input.nextInt();
            input.nextLine();
            System.out.print("Enter Device Type (e.g. Laptop, Server): ");
            String type = input.nextLine();
            System.out.print("Enter Brand: ");
            String brand = input.nextLine();
            System.out.print("Enter Serial Number: ");
            String serial = input.nextLine();

            deviceService.addDevice(devCustId, type, brand, serial);
        } else if (deviceChoice == 2) {
            System.out.print("Enter Customer ID: ");
            int searchCustId = input.nextInt();
            input.nextLine();
            System.out.println(deviceService.getDevicesByCustomerId(searchCustId));
        } else if (deviceChoice == 3) {
            System.out.print("Enter Device ID: ");
            int devId = input.nextInt();
            input.nextLine();
            System.out.println("Select Status: 1) ACTIVE  2) UNDER_MAINTENANCE  3) DECOMMISSIONED");
            int statusOpt = input.nextInt();
            input.nextLine();

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
        System.out.print("Choose an option: ");
        int ticketChoice = input.nextInt();
        input.nextLine();

        if (ticketChoice == 1) {
            System.out.print("Enter Customer ID: ");
            int tCustId = input.nextInt();
            input.nextLine();
            System.out.print("Enter Device ID: ");
            int tDevId = input.nextInt();
            input.nextLine();
            System.out.print("Enter Issue Description: ");
            String desc = input.nextLine();

            ticketService.createTicket(tCustId, tDevId, desc);
        } else if (ticketChoice == 2) {
            System.out.print("Enter Customer ID: ");
            int searchCustId = input.nextInt();
            input.nextLine();
            System.out.println(ticketService.getTicketsByCustomerId(searchCustId));
        } else if (ticketChoice == 3) {
            System.out.print("Enter Ticket ID: ");
            int tId = input.nextInt();
            input.nextLine();
            System.out.println("Select Status: 1) OPEN  2) IN_PROGRESS  3) RESOLVED  4) CLOSED");
            int tStatusOpt = input.nextInt();
            input.nextLine();

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
                            System.out.print("Choose an option: ");
                            int custChoice = input.nextInt();
                            input.nextLine();
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
                System.out.print("Enter Device ID: ");
                int devId = input.nextInt();
                input.nextLine();
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
}
