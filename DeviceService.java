import java.util.ArrayList;

public class DeviceService {
    private ArrayList<Device> devices;
    private int nextDeviceid;
   //Constructor
    DeviceService(){
        this.devices = new ArrayList<>();
        this.nextDeviceid = 101;

    }
    public int generateNextId(){
        return nextDeviceid++;
    }
    
    public void addDevice(int customerId, String deviceType, String brand, String serialNumber){
        int id = generateNextId();
    Device newDevice = new Device(id, customerId, deviceType, brand, serialNumber);
        devices.add(newDevice);   

    }
    
}
