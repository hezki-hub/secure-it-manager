import java.util.ArrayList;
public class CustomerService {
    private ArrayList<Customer> customers;
    private int nextCustomerId;
    CustomerService(){
        this.customers = new ArrayList<>();
        this.nextCustomerId = 101;

    }  
    public int generateNextId(){
        return nextCustomerId++;
    }
  
    public void addCustomer( String username, String password,String  email, String phone, String companyName){
          int id = generateNextId();
        Customer newCustomer = new Customer(id, username, password, email, phone, companyName);
        customers.add(newCustomer);

        System.out.println("Customer Added!!");
    }

    public ArrayList<Customer> getAllCustomers(){
        return this.customers;
        //we will loop through the array in the main class so that it will be easy 
    }

    public Customer findCustomerById(int id){
        for(Customer c : customers){
            if(c.getId() == id){
                return c;
            }
        }
        return null;

    }


   public boolean updateCustomer(int id, String newPhone, String newEmail, String newCompany){
    Customer targetCustomer = findCustomerById(id);
    if(targetCustomer == null){
        return false;
    }else{
        targetCustomer.setPhone(newPhone);
        targetCustomer.setCompanyName(newCompany);
        targetCustomer.setEmail(newEmail);
    }
    return true;
   }
   public boolean deleteCustomer(int id){
     Customer targetCustomer = findCustomerById(id);
     if(targetCustomer == null){
        return false;
     }
        customers.remove(targetCustomer);
     
        return true;

   }
}
