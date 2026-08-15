import java.util.ArrayList;

public class CustomerService {
    private ArrayList<Customer> customers;
    private int nextCustomerId;
    private static final String FILE_PATH = "data/customer.txt";

    public CustomerService() {
        this.customers = new ArrayList<>();
        this.nextCustomerId = 101;
        ArrayList<String> lines = FileManager.loadLines(FILE_PATH);
        for (String line : lines) {
            customers.add(Customer.fromCSV(line));
        }
        int maxId = 0;
        for (Customer c : customers) {
            if (c.getId() > maxId) {
                maxId = c.getId();
            }
        }
        this.nextCustomerId = maxId + 1;

    }

    public void saveAll() {
        ArrayList<String> lines = new ArrayList<>();
        for (Customer c : customers) {
            lines.add(c.toCSV());
        }
        FileManager.saveLine(FILE_PATH, lines);
    }

    public int generateNextId() {
        return nextCustomerId++;
    }

    public void addCustomer(String username, String password, String email, String phone, String companyName) {
        int id = generateNextId();
        Customer newCustomer = new Customer(id, username, password, email, phone, companyName);
        customers.add(newCustomer);
        saveAll();

        System.out.println("Customer Added!!");
    }

    public void getAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No user found!!");

        }
        for (Customer c : customers) {
            System.out.println(c);
        }
    }

    public Customer findCustomerById(int id) {
        for (Customer c : customers) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;

    }

    public boolean updateCustomer(int id, String newPhone, String newEmail, String newCompany) {
        Customer targetCustomer = findCustomerById(id);
        if (targetCustomer == null) {
            return false;
        } else {
            targetCustomer.setPhone(newPhone);
            targetCustomer.setCompanyName(newCompany);
            targetCustomer.setEmail(newEmail);
        }
        saveAll();
        return true;
    }

    public boolean deleteCustomer(int id) {
        Customer targetCustomer = findCustomerById(id);
        if (targetCustomer == null) {
            return false;
        }
        customers.remove(targetCustomer);
        saveAll();

        return true;

    }
}
