import java.util.ArrayList;

public class TicketService {
    private  ArrayList<Ticket> tickets;
    private int nextTicketId;

    TicketService(){
        this.tickets = new ArrayList<>();
        this.nextTicketId = 1;
    }
    public int generateNextTicketId(){
        return nextTicketId++;
    }
    public void  createTicket(int customerId, int deviceId, String description){
        int id = generateNextTicketId();
        Ticket newTicket = new Ticket(id, customerId, deviceId, description);
        tickets.add(newTicket);
    }

    public Ticket findTicketById(int id){
        for(Ticket t : tickets){
            if(t.getId() == id){
                return t;
            }
        }
        return null;
        
    }

    public ArrayList<Ticket> getTicketByCustomerId(int customerId){
        ArrayList<Ticket> customerTickets = new ArrayList<>();
        for(Ticket t : tickets){
            if(t.getCustomerId() == customerId){
                customerTickets.add(t);
            }
           
        }
        return customerTickets;
    }

    public boolean updateTicketStatus(int ticketId, TicketStatus newStatus){
        Ticket targetId = findTicketById(ticketId);
        if(targetId == null){
            return false;
        }
        targetId.setStatus(newStatus);
        return true;
    }
}
