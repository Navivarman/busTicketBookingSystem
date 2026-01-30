import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BusBooking {
    private List<Customer> customerList;
    private List<Bus> busList;
    private int customerIdCounter;
    private int busIdCounter;
    private Customer currentCus;
    public BusBooking(){
        customerList = new ArrayList<>();
        busList = new ArrayList<>();
        customerIdCounter = 1;
        busIdCounter = 1;
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
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BusBooking system = new BusBooking();
        while (true){
            System.out.println("\nBus Ticket Booking System");
            System.out.println("1.Sign Up");
            System.out.println("2.Login");
            System.out.println("3.Show Available Buses");

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
            }
        }
    }
}
