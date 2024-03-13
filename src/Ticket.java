import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// Define a public class named Ticket
public class Ticket {
    // Private instance variables to store row, seat, price, and associated Person
    private String row;
    private int seat;
    private double price;
    private Person person;

    // Constructor to initialize Ticket object with row, seat, price, and associated Person
    public Ticket(String row, int seat, double price, Person person){
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    // Getter methods for retrieving values
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
        return person;
    }

    // Setter methods for updating values
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
        this.person = person;
    }

    // Method to print information about the ticket and associated person
    public void printInfo(){
        this.person.printInfo();
        System.out.println("Ticket information: ");
        System.out.print("Row: " + this.row);
        System.out.println("      Seat: " + this.seat);
        System.out.println("Price: " + this.price);
    }

    // Method to save ticket and person information to a file
    public void save(){
        String name = (this.row + this.seat) + ".txt";
        try {
            FileWriter writer = new FileWriter(name);
            writer.write("--Persons info--\n \n");
            writer.write("Name: " + this.person.getName() +"\n" + "Surname: " + this.person.getSurname() +"\n" + "Email: " + person.getEmail() +"\n\n");
            writer.write("--Ticket info--\n \n");
            writer.write("Row: " + this.row + "\n" + "Seat: " + this.seat + "\n" + "Price: " + this.price);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
        }
    }

    // Method to delete the file associated with the ticket
    public void delete_file(){
        String name = (this.row + this.seat) + ".txt";
        File file = new File(name);
        if(!file.delete()){
            System.out.println("The file relevant to the ticket couldn't be deleted ");
        }
    }
}
