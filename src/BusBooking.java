import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BusBooking {
    private List<Customer> customerList;
    private int customerIdCounter;
    public BusBooking(){
        customerList = new ArrayList<>();
        customerIdCounter = 1;
    }

    public void getSignUp(Scanner scan){
        System.out.print("Enter your name: ");
        String name = scan.nextLine();
        System.out.print("Enter Password: ");
        String password = scan.nextLine();
        System.out.print("Enter age: ");
        int age = scan.nextInt();
        scan.nextLine();
        System.out.print("Enter your gender: ");
        String gender = scan.nextLine();
        Customer customer = new Customer(customerIdCounter++,name,password,age,gender);
        customerList.add(customer);
        System.out.println("Sign up successful! Your customer ID is: " + customer.getCusId());
        System.out.println(customer.toString());
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BusBooking system = new BusBooking();
        while (true){
            System.out.println("\n Bus Ticket Booking System");
            System.out.println("1.Sign Up");
            system.getSignUp(scan);
            break;
        }
    }
}
