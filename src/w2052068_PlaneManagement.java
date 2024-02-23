import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class w2052068_PlaneManagement {
   public static String[][] seatPlan = {{"O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O"},
            {"O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O"},
            {"O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O"},
            {"O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O"}};
   public static Ticket[] tickets = new Ticket[52];

    static void buy_seat(){
        double price;
        int column = 0;
        String row = null;
        boolean check = false;
        Scanner input = new Scanner(System.in);
        while(!check){
            System.out.print("Enter the row letter(A,B,C,D): ");
            row = input.next().toUpperCase();
            if(row.equals("A") || row.equals("B") || row.equals("C") || row.equals("D")){
                check = true;
            }
            else{
                System.out.println("Please enter a valid row number: ");
            }
        }
        check = false;
        while(!check){
            try{
                System.out.print("Enter the column number: ");
                column = input.nextInt();
                if((row.equals("B") || row.equals("C")) && (column < 1 || column > 12)){
                        System.out.println("Please enter a valid column number!!");
                }
                else if(column < 1 || column > 14){
                        System.out.println("Please enter a valid column number!!");
                }
                else{
                    check = true;

                }
            }catch(InputMismatchException ex){
                System.out.println("Please enter an integer!!");
                input.nextLine();
            }
        }
        int rowNum = row.charAt(0);
        if(seatPlan[rowNum - 65][column-1].equals("O")){
            // extended part
            System.out.print("Enter your name: ");
            String name = input.next();
            System.out.print("Enter your surname: ");
            String surname = input.next();
            System.out.print("Enter your email: ");
            String email = input.next();
            Person person = new Person(name,surname,email);
            // calculating ticket cost
            if(column <= 5){
                price = 200;
            }
            else if(column <= 9){
                price = 150;
            }
            else{
                price = 180;
            }
            Ticket ticket = new Ticket(row,column,price,person);
            // start here again
            for(int i = 0; i < 52; i++){
                if(tickets[i] == null){
                    tickets[i] = ticket;
                    break;
                }
            }
            ticket.save();
            seatPlan[rowNum - 65][column-1] = "X";
            System.out.println("Seat successfully bought!!");
        }
        else{
            System.out.println("Seat is already bought!!");
        }
    }

    static void cancel_seat(){
        int column = 0;
        String row = null;
        boolean check = false;
        Scanner input = new Scanner(System.in);
        while(!check){
            System.out.print("Enter the row letter(A,B,C,D): ");
            row = input.next().toUpperCase();
            if(row.equals("A") || row.equals("B") || row.equals("C") || row.equals("D")){
                check = true;
            }
            else{
                System.out.println("Please enter a valid row number: ");
            }
        }
        check = false;
        while(!check){
            try{
                System.out.print("Enter the column number: ");
                column = input.nextInt();
                if((row.equals("B") || row.equals("C")) && (column < 1 || column > 12)){
                    System.out.println("Please enter a valid column number!!");
                }
                else if(column < 1 || column > 14){
                    System.out.println("Please enter a valid column number!!");
                }
                else{
                    check = true;

                }
            }catch(InputMismatchException ex){
                System.out.println("Please enter an integer!!");
                input.nextLine();
            }
        }
        int rowNum = row.charAt(0);
        if(seatPlan[rowNum - 65][column-1].equals("X")){
            seatPlan[rowNum - 65][column-1] = "O";
            for(int i = 0; i < 52; i++){
                if(tickets[i] != null && tickets[i].getRow().equals(row) && tickets[i].getSeat() == column){
                    tickets[i] = null;
                }
            }
            System.out.println("Seat successfully cancelled!!");
        }
        else{
            System.out.println("Seat is already available!!");
        }

    }

    static void find_first_available(){
        boolean found = false;
        for(int row = 0; row < 4; row++){
            if(found){
                break;
            }
            for(int column = 0; column < seatPlan[row].length; column++){
                if(seatPlan[row][column].equals("O")){
                    char rowLetter = (char) (row + 65);
                    System.out.println("Seat available at row: " + rowLetter + " column: " + (column+1));
                    found = true;
                    break;
                }
            }
        }
        if(!found){
            System.out.println("All the seats are bought!!");
        }
    }

    static void show_seating_plan(){
        for(int row = 0; row < 4; row++){
            for(String element : seatPlan[row]){
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    static void print_tickets_info(){
        double total = 0;
        System.out.println("--Details of all the tickets sold--");
        for(Ticket element : tickets){
            if(element != null) {
                element.printInfo();
                total += element.getPrice();
            }
        }
        System.out.println("The total amount of the tickets is : " + total);
    }

    static void search_ticket(){
        Scanner input = new Scanner(System.in);
        String row = null;
        int column = 0;
        boolean check = false;
        while(!check){
            System.out.print("Enter the row letter(A,B,C,D): ");
            row = input.next().toUpperCase();
            if(row.equals("A") || row.equals("B") || row.equals("C") || row.equals("D")){
                check = true;
            }
            else{
                System.out.println("Please enter a valid row number: ");
            }
        }
        check = false;
        while(!check){
            try{
                System.out.print("Enter the column number: ");
                column = input.nextInt();
                if((row.equals("B") || row.equals("C")) && (column < 1 || column > 12)){
                    System.out.println("Please enter a valid column number!!");
                }
                else if(column < 1 || column > 14){
                    System.out.println("Please enter a valid column number!!");
                }
                else{
                    check = true;

                }
            }catch(InputMismatchException ex){
                System.out.println("Please enter an integer!!");
                input.nextLine();
            }
        }
        int rowNum = row.charAt(0);
        if(seatPlan[rowNum - 65][column-1].equals("X")) {
            for (Ticket element : tickets) {
                if (element != null) {
                    if (element.getRow().equals(row) && element.getSeat() == column) {
                        element.printInfo();
                    }
                }
            }
        }
        else{
            System.out.println("This seat is available");
        }


    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Plane Management application");
        boolean check;
        int option = 0;
        boolean exit = false;
        while(!exit) {
            check = false;
            while (!check) {
                System.out.println("***************************************************");
                System.out.println("*                      Menu                       *");
                System.out.println("***************************************************");
                System.out.println("   1) Buy seat");
                System.out.println("   2) Cancel a seat");
                System.out.println("   3) Find the first seat available");
                System.out.println("   4) Show the seating plan");
                System.out.println("   5) Print tickets information and total sales");
                System.out.println("   6) Search ticket");
                System.out.println("   0) Quit");
                System.out.println("***************************************************");

                try {
                    System.out.print("Please select an option: ");
                    option = input.nextInt();
                    if (option < 0 || option > 6) {
                        System.out.println("Please enter a valid option!");
                    } else {
                        check = true;
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Please enter an integer!!");
                    input.nextLine();
                }
            }
            switch (option) {
                case 0:
                    exit = true;
                    break;
                case 1:
                    buy_seat();
                    break;
                case 2:
                    cancel_seat();
                    break;
                case 3:
                    find_first_available();
                    break;
                case 4:
                    show_seating_plan();
                    break;
                case 5:
                    print_tickets_info();
                    break;
                case 6:
                    search_ticket();
                    break;

            }
        }
    }
}
