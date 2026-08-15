import java.util.ArrayList;

public class DeviceService {
    private ArrayList<Device> devices;
    private int nextDeviceid;
    private static final String FILE_PATH = "data/devices.txt";

    // Constructor
    public DeviceService() {
        this.devices = new ArrayList<>();
        this.nextDeviceid = 101;
        ArrayList<String> lines = FileManager.loadLines(FILE_PATH);
        for (String line : lines) {
            devices.add(Device.fromCSV(line));
        }
        int maxId = 0;
        for (Device d : devices) {
            if (d.getId() > maxId) {
                maxId = d.getId();
            }
        }
        this.nextDeviceid = maxId + 1;
    }

    public void saveAll() {
        ArrayList<String> lines = new ArrayList<>();
        for (Device d : devices) {
            lines.add(d.toCSV());
        }
        FileManager.saveLine(FILE_PATH, lines);
    }

    public int generateNextId() {
        return nextDeviceid++;
    }

    public void addDevice(int customerId, String deviceType, String brand, String serialNumber, DeviceStatus status) {
        int id = generateNextId();
        Device newDevice = new Device(id, customerId, deviceType, brand, serialNumber, status);
        devices.add(newDevice);

        saveAll(); // Save to disk immediately after updating memory!

    }

    

    public Device findDeviceById(int deviceId) {
        for (Device d : devices) {
            if (d.getId() == deviceId) {
                return d;
            }
        }
        return null;
    }

    public boolean updateDeviceStatus(int deviceId, DeviceStatus newStatus) {
        Device targetDevice = findDeviceById(deviceId);
        if (targetDevice == null) {
            return false;
        }

        targetDevice.setStatus(newStatus);

        saveAll(); // Save to disk immediately after updating memory!

        return true;

    }

    public ArrayList<Device> getDevicesByCustomerId(int customerId) {
        ArrayList<Device> customerDevice = new ArrayList<>();
        for (Device d : devices) {
            if (d.getCustomerId() == customerId) {
                customerDevice.add(d);
            }
        }
        return customerDevice;
    }

}
