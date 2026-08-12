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

    public Device findDeviceById(int deviceId){
        for(Device d : devices){
            if(d.getId() == deviceId){
                return d;
            }
        }
        return null;
    }

    public boolean updateDeviceStatus(int deviceId, DeviceStatus newStatus){
        Device targetDevice = findDeviceById(deviceId);
    if (targetDevice == null) {
        return false;
    }
    targetDevice.setStatus(newStatus);
    return true;
    }
    public ArrayList<Device> getDevicesByCustomerId(int customerId){
        ArrayList<Device> customerDevice = new ArrayList<>();
        for(Device d : devices){
            if(d.getCustomerId() == customerId){
                customerDevice.add(d);
            }
        }
        return customerDevice;
    }
  
}
