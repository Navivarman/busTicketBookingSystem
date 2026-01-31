import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private int ticketId;
    private List<Integer> bookedSeats;
    private Bus bus;
    private int numberOfTickets;
    private double fare;
    private int customerId;

    public Ticket(int ticketId, Bus bus, int numberOfTickets, double fare,int customerId){
        this.ticketId = ticketId;
        this.bus = bus;
        this.numberOfTickets = numberOfTickets;
        this.fare = fare;
        this.customerId = customerId;
        this.bookedSeats = new ArrayList<>();
    }

    public int getTicketId(){ return ticketId;}
    public List<Integer> getBookedSeats(){ return bookedSeats;}
    public Bus getBus(){ return bus;}
    public int getNumberOfTickets(){ return numberOfTickets;}
    public double getFare(){ return fare;}
    public int getCustomerId(){ return customerId;}
    public void setBookedSeats(int seatNo){
        bookedSeats.add(seatNo);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + ticketId +
                ", bookedSeats=" + bookedSeats +
                ", bus=" + bus +
                ", numberOfTickets=" + numberOfTickets +
                ", fare=" + fare +
                ", customerId=" + customerId +
                '}';
    }
}
