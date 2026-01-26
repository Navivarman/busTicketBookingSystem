public class Bus {
    private int busId;
    private String busName;
    private String busType;
    private String seatType;
    private int totalSeats;
    private boolean[] seats;

    public Bus(int busId,String busName,String busType,String seatType,int totalSeats){
        this.busId = busId;
        this.busName = busName;
        this.busType = busType;
        this.seatType = seatType;
        this.totalSeats = totalSeats;
        this.seats = new boolean[totalSeats];
    }

    public int getBusId(){ return busId;}
    public String getbusName(){ return busName;}
    public String getbusType(){ return busType;}
    public String getseatType(){ return seatType;}
    public int getTotalSeats(){ return totalSeats;}

    public int getAvailableSeats(){
        int count = 0;
        for(boolean seat : seats){
            if(!seat) count++;
        }
        return count;
    }
    public boolean bookSeat(int seatNumber) {
        if (seatNumber < 0 || seatNumber >= totalSeats) {
            return false;
        }
        if (!seats[seatNumber]) {
            seats[seatNumber] = true;
            return true;
        }
        return false;
    }

    public boolean cancelSeat(int seatNumber) {
        if (seatNumber < 0 || seatNumber >= totalSeats) {
            return false;
        }
        if (seats[seatNumber]) {
            seats[seatNumber] = false;
            return true;
        }
        return false;
    }
    public void showAvailableSeats(){
        System.out.print(busName + " available seats are: ");
        for(int i = 1; i <= totalSeats; i++){
            if(!seats[i-1]){
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
    @Override
    public String toString() {
        return "Bus{" +
                "id=" + busId +
                ", busName='" + busName + '\'' +
                ", busType='" + busType + '\'' +
                ", seatType='" + seatType + '\'' +
                ", totalSeats=" + totalSeats +
                ", availableSeats=" + getAvailableSeats() +
                '}';
    }
}
