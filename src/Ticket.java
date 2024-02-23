import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private String row;
    private int seat;
    private double price;
    private Person Person;

    public Ticket(String row, int seat, double price, Person Person){
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.Person = Person;
    }

    public String getRow() {
        return row;
    }
    public int getSeat() {
        return seat;
    }
    public double getPrice() {
        return price;
    }
    public Person getPerson() {
        return Person;
    }

    public void setRow(String row) {
        this.row = row;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setPerson(Person person) {
        Person = person;
    }

    public void printInfo(){
        Person.printInfo();
        System.out.println("Ticket information: ");
        System.out.print("Row: " + row);
        System.out.println("      Seat: " + seat);
        System.out.println("Price: " + price);
    }

    public void save(){
        String name = (row + seat) + ".txt";
        File file = new File(name);
        try {
            FileWriter writer = new FileWriter(name);
            writer.write("Persons info : /n");
            writer.write("Name: " + Person.getName() +"/n" + "Surname: " + Person.getSurname() +"/n" + "Email: " + Person.getSurname() +"/n");
            writer.write("Ticket info : /n");
            writer.write("Row: " + row + "/n" + "Seat: " + seat + "/n" + "Price: " + price);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
        }

    }
}
