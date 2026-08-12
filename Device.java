public class Device{
    private int id;
    private String deviceType;
    private String brand;
    private String serialNumber;
    private DeviceStatus status;
    private int customerId;
    public Device(int id, int customerId, String deviceType, String brand, String serialNumber){
        this.id = id;
        this.customerId = customerId;
        this.deviceType = deviceType;
        this.brand = brand;
        this.serialNumber =serialNumber;
        this.status = DeviceStatus.ACTIVE;
    } 
    //Getter methods 
    public int getId(){
        return this.id;
    }   
    public int getCustomerId(){
        return this.customerId;
    }
    public String getDeviceType(){
        return this.deviceType;

    }
    public String getBrand(){
        return this.brand;
    }   
    public String getSerialNumber(){
        return this.serialNumber;
    }
    public DeviceStatus getStatus(){
        return this.status = DeviceStatus.ACTIVE;
    }
    //Setter methods

    public void setStatus(DeviceStatus status){
        this.status = status;
    }
    public void setDeviceType(String deviceType){
        this.deviceType = deviceType;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }


}