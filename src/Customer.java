public class Customer {
    private int cusId;
    private String cusName;
    private String password;
    private int age;
    private String gender;

    public Customer(int cusId,String cusName,String password,int age,String gender){
        this.cusId = cusId;
        this.cusName = cusName;
        this.password = password;
        this.age = age;
        this.gender = gender;
    }

    public int getCusId(){ return cusId;}
    public String getCusName(){ return cusName;}
    public String getPassword(){ return password;}
    public int getAge(){ return age;}
    public String getGender(){ return gender;}
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + cusId +
                ", name='" + cusName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
