public class Customer extends User {

    private String phone;
    private String email;
    private String companyName;

    public Customer(int id, String username, String password, String email, String phone, String companyName) {
        super(id, username, password, "Customer");
        this.companyName = companyName;
        this.email = email;
        this.phone = phone;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public String getPhone() {
        return this.phone;

    }

    public String getEmail() {
        return this.email;
    }

    public String toCSV() {
        return getId() + "," + getUserName() + "," + email + "," + phone + "," + getPassword() + "," + companyName;
    }

    public static Customer fromCSV(String csvLine) {
        String[] data = csvLine.split(",");
        int id = Integer.parseInt(data[0].trim());
        String username = data[1];
        String email = data[2];
        String phone = data[3];
        String password = data[4];
        String companyName = data[5];
        return new Customer(id, username, password, email, phone, companyName);
    }

}
