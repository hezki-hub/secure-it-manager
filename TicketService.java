import java.util.ArrayList;

public class TicketService {
    private ArrayList<Ticket> tickets;
    private int nextTicketId;
    private static final String FILE_PATH = "data/ticket.txt";

    public TicketService() {
        this.tickets = new ArrayList<>();
        this.nextTicketId = 1;
        ArrayList<String> lines = FileManager.loadLines(FILE_PATH);
        for (String line : lines) {
            tickets.add(Ticket.fromCSV(line));
        }
        int maxId = 0;
        for (Ticket t : tickets) {
            if (t.getId() > maxId) {
                maxId = t.getId();
            }
        }
        this.nextTicketId = maxId + 1;
    }

    public void saveAll() {
        ArrayList<String> lines = new ArrayList<>();
        for (Ticket t : tickets) {
            lines.add(t.toCSV());
        }
        FileManager.saveLine(FILE_PATH, lines);
    }

    public int generateNextTicketId() {
        return nextTicketId++;
    }

    public void createTicket(int customerId, int deviceId, String description) {
        int id = generateNextTicketId();
        Ticket newTicket = new Ticket(id, customerId, deviceId, description);
        tickets.add(newTicket);
        saveAll();
    }

    public Ticket findTicketById(int id) {
        for (Ticket t : tickets) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;

    }

    public void getAllTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No ticket found!!");
            return;
        }
        for (Ticket t : tickets) {
            System.out.println(t);

        }
    }

    public ArrayList<Ticket> getTicketsByCustomerId(int customerId) {
        ArrayList<Ticket> customerTickets = new ArrayList<>();
        for (Ticket t : tickets) {
            if (t.getCustomerId() == customerId) {
                customerTickets.add(t);
            }

        }
        return customerTickets;
    }

    public boolean updateTicketStatus(int ticketId, TicketStatus newStatus) {
        Ticket targetId = findTicketById(ticketId);
        if (targetId == null) {
            return false;
        }
        targetId.setStatus(newStatus);
        saveAll();
        return true;
    }
}
