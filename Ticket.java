public class Ticket {
    private int id;
    private int customerId;
    private int deviceId;
    private String description;
    private TicketStatus status;

    // constructor
    public Ticket(int id, int customerId, int deviceId, String description) {
        this.id = id;
        this.customerId = customerId;
        this.description = description;
        this.deviceId = deviceId;
        this.status = TicketStatus.OPEN;

    }

    // getters
    public int getId() {
        return this.id;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public int getDeviceId() {
        return this.deviceId;
    }

    public String getDescription() {
        return this.description;
    }

    public TicketStatus getStatus() {
        return this.status;
    }

    // Setter
    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public String toCSV() {
        return id + "," + customerId + "," + deviceId + "," + description;
    }

    public static Ticket fromCSV(String csvLine) {
        String[] data = csvLine.split(",");
        int id = Integer.parseInt(data[0].trim());
        int customerId = Integer.parseInt(data[1].trim());
        int deviceID = Integer.parseInt(data[2].trim());
        String description = data[3];

        return new Ticket(id, customerId, deviceID, description);

    }
}
