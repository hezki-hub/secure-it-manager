import java.util.ArrayList;
public class CustomerService {
    private ArrayList<customer> customers;
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
        customer newCustomer = new customer(id, username, password, email, phone, companyName);
        customers.add(newCustomer);

        System.out.println("Customer Added!!");
    }

    public ArrayList<customer> getAllCustomer(){
        return this.customers;
        //we will loop through the array in the main class so that it will be easy 
    }

    public customer findCustomerById(int id){
        for(customer c : customers){
            if(c.getId() == id){
                return c;
            }
        }
        return null;

    }


   public boolean updateCustomer(int id, String newPhone, String newEmail, String newCompany){
    customer targetCustomer = findCustomerById(id);
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
     customer targetCustomer = findCustomerById(id);
     if(targetCustomer == null){
        return false;
     }
        customers.remove(targetCustomer);
     
        return true;

   }
}
