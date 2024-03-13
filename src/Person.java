// Define a public class named Person
public class Person {
    // Private instance variables to store name, surname, and email
    private String name;
    private String surname;
    private String email;

    // Constructor to initialize Person object with name, surname, and email
    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    // Getter method to retrieve the name
    public String getName() {
        return name;
    }

    // Getter method to retrieve the surname
    public String getSurname() {
        return surname;
    }

    // Getter method to retrieve the email
    public String getEmail() {
        return email;
    }

    // Setter method to update the name
    public void setName(String name) {
        this.name = name;
    }

    // Setter method to update the surname
    public void setSurname(String surname) {
        this.surname = surname;
    }

    // Setter method to update the email
    public void setEmail(String email) {
        this.email = email;
    }

    // Method to print information about the person
    public void printInfo() {
        System.out.println("\nName: " + this.name);
        System.out.println("Surname: " + this.surname);
        System.out.println("Email: " + this.email);
    }
}

