package package2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {

    String username;
    String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;

    }

}

class Reservation {
    String trainNumber;
    String trainName;
    String classType;
    String dateOfJourney;
    String from;
    String to;

    Reservation(String trainNumber, String trainName, String classType, String dateOfJourney, String from, String to) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
    }
}

class OnlineReservationSystem {
    private Map<String, User> users = new HashMap<>();
    private Map<String, Reservation> reservations = new HashMap<>();

    void registerUser(String username, String password) {
        users.put(username, new User(username, password));
    }

    boolean loginUser(String username, String password) {
        User user = users.get(username);
        return user != null && user.password.equals(password);
    }

    void makeReservation(String username, Reservation reservation) {
        reservations.put(generatePNR(), reservation);
        System.out.println("Reservation successful!");
        System.out.println("Your PNR Is 47140999");
    }

    Reservation cancelReservation(String pnr) {
        Reservation reservation = reservations.get(pnr);
        if (reservation != null) {
            reservations.remove(pnr);
            System.out.println("Cancellation successful!");
            return reservation; 
        } else {
            System.out.println("Invalid PNR number. Cancellation failed.");
            return null; 
        }
    }

    private String generatePNR() {
        return "47140999";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OnlineReservationSystem reservationSystem = new OnlineReservationSystem();

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        reservationSystem.registerUser(username, password);

        System.out.print("Enter your username for login: ");
        String loginUsername = scanner.nextLine();
        System.out.print("Enter your password for login: ");
        String loginPassword = scanner.nextLine();

        if (reservationSystem.loginUser(loginUsername, loginPassword)) {
            System.out.println("Enter reservation details:");
            System.out.print("Train Number: ");
            String trainNumber = scanner.nextLine();
            System.out.print("Train Name: ");
            String trainName = scanner.nextLine();
            System.out.print("Class Type: ");
            String classType = scanner.nextLine();
            System.out.print("Date of Journey: ");
            String dateOfJourney = scanner.nextLine();
            System.out.print("From: ");
            String from = scanner.nextLine();
            System.out.print("To: ");
            String to = scanner.nextLine();

            Reservation reservation = new Reservation(trainNumber, trainName, classType, dateOfJourney, from, to);
            reservationSystem.makeReservation(loginUsername, reservation);

            System.out.print("Enter PNR number for cancellation: ");
            String pnr = scanner.nextLine();
            Reservation cancelledReservation = reservationSystem.cancelReservation(pnr);

            if (cancelledReservation != null) {
         
                System.out.println("Cancelled Reservation Details:");
                System.out.println("Train Number: " + cancelledReservation.trainNumber);
                System.out.println("Train Name: " + cancelledReservation.trainName);
                System.out.println("Class Type: " + cancelledReservation.classType);
                System.out.println("Date of Journey: " + cancelledReservation.dateOfJourney);
                System.out.println("From: " + cancelledReservation.from);
                System.out.println("To: " + cancelledReservation.to);

                System.out.print("Press 'OK' to confirm cancellation: ");
                String confirmation = scanner.nextLine();
                if (confirmation.equalsIgnoreCase("OK")) {
                    System.out.println("Cancellation confirmed!");
                } else {
                    System.out.println("Cancellation not confirmed. Reservation still active.");
                }
            } else {
                System.out.println("Invalid PNR number. Cancellation failed.");
            }
        } else {
            System.out.println("Invalid username or password. Login failed.");
        }

        scanner.close();
    }
}