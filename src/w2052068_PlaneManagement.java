import java.util.InputMismatchException;
import java.util.Scanner;

public class w2052068_PlaneManagement {
    // 2D array to represent the seating plan of the plane
    public static int[][] seatPlan = new int[4][];
    // Array to store ticket objects
    public static Ticket[] tickets = new Ticket[52];

    /**
     * Method to validate and get the row input from the user
     * @return the validated row
     */
    static String row_input_validate(){
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
                System.out.println("Please enter a valid row letter.\n");
            }
        }
        return row;
    }


    /**
     * Method to validate and get the seat input from the user
     * @param row takes the row letter as a parameter
     * @return the validated seat
     */
    static int seat_input_validate(String row) {
        Scanner input = new Scanner(System.in);
        int seat = 0;
        boolean check = false;
        while (!check) {
            try {
                System.out.print("Enter the seat number: ");
                seat = input.nextInt();
                // Check seat validity based on row
                if ((row.equals("B") || row.equals("C")) && (seat < 1 || seat > 12)) {
                    System.out.println("Please enter a valid seat number!!\n");
                } else if (seat < 1 || seat > 14) {
                    System.out.println("Please enter a valid seat number!!\n");
                } else {
                    check = true;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter an integer!!\n");
                input.nextLine();
            }
        }
        return seat;
    }

    // Method to buy a seat

    /**
     * Method to buy a seat and stores buyers details and ticket details in an array
     */
    static void buy_seat(){
        Scanner input = new Scanner(System.in);
        double price;
        String row = row_input_validate();
        int seat = seat_input_validate(row);
        int rowNum = row.charAt(0);
        if(seatPlan[rowNum - 65][seat-1] == 0){
            // Get passenger information
            System.out.print("Enter your name: ");
            String name = input.next();
            System.out.print("Enter your surname: ");
            String surname = input.next();
            System.out.print("Enter your email: ");
            String email = input.next();
            Person person = new Person(name,surname,email);

            // Calculate ticket cost based on seat position
            if(seat <= 5){
                price = 200;
            }
            else if(seat <= 9){
                price = 150;
            }
            else{
                price = 180;
            }
            // Create and store the ticket
            Ticket ticket = new Ticket(row,seat,price,person);
            for(int i = 0; i < 52; i++){
                if(tickets[i] == null){
                    tickets[i] = ticket;
                    break;
                }
            }
            ticket.save();
            // Mark the seat as bought in the seating plan
            seatPlan[rowNum - 65][seat-1] = 1;
            System.out.println("\nSeat successfully bought!!\n");
        }
        else{
            System.out.println("\nSeat is already bought!!\n");
        }
    }


    /**
     * Method to cancel a seat and removes the details from the array. deletes the file containing the details of the relevant ticket
     */
    static void cancel_seat(){
        Ticket ticket = null;
        String row = row_input_validate();
        int seat = seat_input_validate(row);
        int rowNum = row.charAt(0);
        if(seatPlan[rowNum - 65][seat-1] == 1){
            // Mark the seat as available in the seating plan
            seatPlan[rowNum - 65][seat-1] = 0;
            // Find and delete the corresponding ticket
            for(int i = 0; i < 52; i++){
                if(tickets[i] != null && tickets[i].getRow().equals(row) && tickets[i].getSeat() == seat){
                    ticket = tickets[i];
                    tickets[i] = null;
                    break;
                }
            }
            // Delete the ticket if found
            if (ticket != null) {
                ticket.delete_file();
            }
            System.out.println("\nSeat successfully cancelled!!\n");
        }
        else{
            System.out.println("\nSeat is already available!!\n");
        }
    }


    /**
     * Method to find the first available seat
     */
    static void find_first_available(){
        boolean found = false;
        for(int row = 0; row < 4; row++){
            if(found){
                break;
            }
            for(int seat = 0; seat < seatPlan[row].length; seat++){
                if(seatPlan[row][seat] == 0){
                    char rowLetter = (char) (row + 65);
                    System.out.println("/nSeat available at : " + rowLetter + (seat+1) + "\n");
                    found = true;
                    break;
                }
            }
        }
        if(!found){
            System.out.println("All the seats are bought!!\n");
        }
    }


    /**
     * Method to display the seating plan
     */
    static void show_seating_plan(){
        for(int row = 0; row < 4; row++){
            for(int element : seatPlan[row]){
                if(element == 0){
                    System.out.print("O ");
                }
                else{
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }


    /**
     * Method to print information about all sold tickets
     */
    static void print_tickets_info(){
        double total = 0;
        System.out.println("--Details of all the tickets sold--");
        for(Ticket element : tickets){
            if(element != null) {
                element.printInfo();
                total += element.getPrice();
                System.out.println();
            }
        }
        if(total == 0){
            System.out.println("\nNo tickets were sold!\n");
        }else{
            System.out.println("The total amount of the tickets is : " + total + "\n");
        }
    }


    /**
     *  Method to search for a specific ticket
     */
    static void search_ticket(){
        String row = row_input_validate();
        int seat = seat_input_validate(row);
        int rowNum = row.charAt(0);
        if(seatPlan[rowNum - 65][seat-1] == 1) {
            for (Ticket element : tickets) {
                if (element != null) {
                    if (element.getRow().equals(row) && element.getSeat() == seat) {
                        element.printInfo();
                        System.out.println();
                    }
                }
            }
        }
        else{
            System.out.println("This seat is available\n");
        }
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Plane Management application");
        // Initialize the seating plan arrays
        seatPlan[0] = new int[14];
        seatPlan[1] = new int[12];
        seatPlan[2] = new int[12];
        seatPlan[3] = new int[14];
        boolean check;
        int option = 0;
        boolean exit = false;
        // Main menu loop
        while(!exit) {
            check = false;
            // Display menu options and get user input
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
                    System.out.println();
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
            // Perform actions based on user choice
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

