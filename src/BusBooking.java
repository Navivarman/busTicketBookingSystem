import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BusBooking {
    private List<Customer> customerList;
    private List<Bus> busList;
    private List<Ticket> ticketList;
    private int customerIdCounter;
    private int busIdCounter;
    private int ticketIdCount;
    private Customer currentCus;
    public BusBooking(){
        customerList = new ArrayList<>();
        busList = new ArrayList<>();
        ticketList = new ArrayList<>();
        customerIdCounter = 1;
        busIdCounter = 1;
        ticketIdCount = 1;
        currentCus = null;
        initializeBuses();
    }

    public void initializeBuses(){
        busList.add(new Bus(busIdCounter++, "Roja","AC", "Seater", 30));
        busList.add(new Bus(busIdCounter++, "Mayura","Non-AC", "Seater", 40));
        busList.add(new Bus(busIdCounter++, "Essar","AC", "Sleeper", 20));
        busList.add(new Bus(busIdCounter++, "CGR","Non-AC", "Sleeper", 25));
    }
    public void getSignUp(Scanner scan){
        System.out.println("Enter your name: ");
        String name = scan.nextLine();
        System.out.println("Enter Password: ");
        String password = scan.nextLine();
        System.out.println("Enter age: ");
        int age = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter your gender: ");
        String gender = scan.nextLine();
        Customer customer = new Customer(customerIdCounter++,name,password,age,gender);
        customerList.add(customer);
        System.out.println("Sign up successful! Your customer ID is: " + customer.getCusId());
        System.out.println(customer.toString());
    }
    public boolean getLogin(Scanner scan){
        System.out.println("Enter your ID: ");
        int id = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter Password: ");
        String password = scan.nextLine();
        for(Customer cus : customerList){
            if(cus.getCusId() == id && cus.getPassword().equals(password)){
                currentCus = cus;
                System.out.println("Login successful! Welcome, " + cus.getCusName());
                return true;
            }
        }
       System.out.println("Invalid Credentials");
        return false;

    }
    public void showAvailabeBuses(){
        System.out.println("\nAvailable buses are: ");
        for(Bus buses : busList){
            System.out.println(buses);
        }
    }
    public void bookingTickets(Scanner scan){
        if(currentCus == null){
            System.out.println("Please login first!");
            return;
        }
        System.out.println("Available Buses are: ");
        showAvailabeBuses();
        System.out.println("Enter Bus ID: ");
        int busId = scan.nextInt();
        Bus selectedBus = null;
        for(Bus bus : busList){
            if(bus.getBusId() == busId){
                selectedBus = bus;
                break;
            }
        }
        if(selectedBus == null){
            System.out.println("Bus does not exists, please ensure bus ID.");
            return;
        }

        System.out.println("Enter number of your Tickets: ");
        int numOfTickets = scan.nextInt();
        if(numOfTickets > selectedBus.getAvailableSeats()){
            System.out.println("Not enough available seats!");
            return;
        }

        System.out.println("Available Seats: ");
        selectedBus.showAvailableSeats();

        List<Integer> seatsToBook = new ArrayList<>();
        for(int i = 0; i < numOfTickets; i++){
            System.out.println("Enter seat number for ticket-" + (i + 1) + ": ");
            int seatNo = scan.nextInt();
            scan.nextLine();
            if(seatNo <= 0 || seatNo > selectedBus.getTotalSeats()){
                System.out.println("Invalid seat number!");
                i--;
                continue;
            }

            if(!selectedBus.bookSeat(seatNo - 1)){
                System.out.println("Seat already booked. Try another seat.");
                i--;
                continue;
            }
            if(seatsToBook.contains(seatNo)){
                System.out.println("You already selected this seat!");
                i--;
                continue;
            }
            seatsToBook.add(seatNo);
        }
        double fare = selectedBus.getbusType().equals("AC") ? 1000 : 500;
        fare += selectedBus.getseatType().equals("Sleeper") ? 500 : 100;
        double totalFare = numOfTickets * fare;

        Ticket ticket = new Ticket(ticketIdCount++,selectedBus,numOfTickets,totalFare,currentCus.getCusId());
        for(int seat : seatsToBook){
            ticket.setBookedSeats(seat);
        }
        ticketList.add(ticket);
        System.out.println("\nBooking successful!");
        System.out.println("Ticket ID: " + ticket.getTicketId());
        System.out.println("Total Fare: " + totalFare);
        System.out.println("Booked Seats: " + seatsToBook);
    }

    public void viewTickets(){
        if(currentCus == null){
            System.out.println("Please login firest!");
        }

        System.out.println("\nYour Tickets: ");
        boolean found = false;
        for(Ticket ticket : ticketList){
            if(ticket.getCustomerId() == currentCus.getCusId()){
                System.out.println(ticket);
                found = true;
            }
        }
        if(!found){
            System.out.println("No ticket found!");
        }
    }
    public void cancelTickets(Scanner scan){
        if(currentCus == null){
            System.out.println("Please login first!");
            return;
        }

        viewTickets();
        System.out.println("Enter ticket ID to cancel: ");
        int ticketId = scan.nextInt();
        scan.nextLine();

        Ticket ticketToCancel = null;
        for(Ticket ticket : ticketList){
            if(ticket.getTicketId() == ticketId && ticket.getCustomerId() == currentCus.getCusId()){
                ticketToCancel = ticket;
            }
        }
        if(ticketToCancel == null){
            System.out.println("No ticket found");
            return;
        }

        Bus bus = ticketToCancel.getBus();
        for(int seat : ticketToCancel.getBookedSeats()){
            bus.cancelSeat(seat);
        }
        ticketList.remove(ticketToCancel);
        System.out.println("Ticket cancel successfully!");
        System.out.println("Refund of " + ticketToCancel.getFare() + " will be return 2 or 3 days.");
    }
    public void showBusSummary() {
        System.out.println("\nBus Summary:");
        for (Bus bus : busList) {
            System.out.println("Bus ID: " + bus.getBusId());
            System.out.println("Type: " + bus.getbusType());
            System.out.println("Seat Type: " + bus.getseatType());
            System.out.println("Total Seats: " + bus.getTotalSeats());
            System.out.println("Booked Seats: " + (bus.getTotalSeats() - bus.getAvailableSeats()));
            System.out.println("Available Seats: " + bus.getAvailableSeats());
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BusBooking system = new BusBooking();
        while (true){
            System.out.println("\nBus Ticket Booking System");
            System.out.println("1.Sign Up");
            System.out.println("2.Login");
            System.out.println("3.Show Available Buses");
            System.out.println("4.Booking seats");
            System.out.println("5.View Tickets");
            System.out.println("6.Cancel Tickets");
            System.out.println("7.Show Summary Bus");
            System.out.println("8.Exit");

            System.out.print("\nEnter your Choice: ");
            int choice = scan.nextInt();
            scan.nextLine();
            switch (choice){
                case 1:
                    system.getSignUp(scan);
                    break;
                case 2:
                    system.getLogin(scan);
                    break;
                case 3:
                    system.showAvailabeBuses();
                    break;
                case 4:
                    system.bookingTickets(scan);
                    break;
                case 5:
                    system.viewTickets();
                    break;
                case 6:
                    system.cancelTickets(scan);
                    break;
                case 7:
                    system.showBusSummary();
                    break;
                case 8:
                    System.out.println("Thank you for using Bus Ticket Booking System!");
                    scan.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
