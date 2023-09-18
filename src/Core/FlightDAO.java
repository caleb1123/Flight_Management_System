/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import static checkValidation.checkValid.sc;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

/**
 *
 * @author ryy15
 */
public class FlightDAO {

    HashMap<String, Flight> flights;
    HashMap<String, Reservation> reservations;
    String f1Name = "C:\\Users\\ryy15\\OneDrive\\Documents\\NetBeansProjects\\Flight_Management_System\\src\\data\\Flight.dat";
    
    public FlightDAO() {
        flights = new HashMap<>();
        reservations = new HashMap<>();
    }

    public boolean searchFlightByID(String F_number) {
        F_number = F_number.trim();
        if (flights.isEmpty() == true) {
            return false;
        }
        if (flights.keySet().contains(F_number) == true) {
            return true;
        }

        return false;
    }

    public Flight searchFlightID(String F_number) {
        F_number = F_number.trim();
        if (flights.isEmpty() == true) {
            return null;
        }
        if (flights.keySet().contains(F_number) == true) {
            return flights.get(F_number);
        }

        return null;
    }

    public Flight searchAvailable(int Available) {
        if (flights.isEmpty() == true) {
            return null;
        }
        if (flights.keySet().contains(Available) == true) {
            return flights.get(Available);
        }

        return null;
    }

    public Reservation searchReservationtID(String reservationId) {
        reservationId = reservationId.trim();
        if (reservations.isEmpty() == true) {
            return null;
        }
        if (reservations.keySet().contains(reservationId) == true) {
            return reservations.get(reservationId);
        }

        return null;
    }

    public void Add_Flight() {
        String F_number, Departure_C, Destination_c;
        LocalTime Departure_Time;
        LocalTime Arrival_Time;
        int Available;
        boolean duplicated;
        String Again;
        String date;
        int initialAvailableSeats;
        do {
            do {
                F_number = checkValidation.checkValid.InputPattern("Flight Number:", "F[\\d]{4}").trim().toUpperCase();
                duplicated = searchFlightByID(F_number);
                if (duplicated == true) {
                    System.out.println("The Flight already exist "
                            + "Input another one!");
                }
            } while (duplicated == true);
            Departure_C = checkValidation.checkValid.checkLength("Departure City: ", 3, 50);

            Destination_c = checkValidation.checkValid.checkLength("Destination city: ", 3, 50);

            Departure_Time = checkValidation.checkValid.inputTime("Departure Time:");

            Arrival_Time = checkValidation.checkValid.inputTime("Arrival Time:");
            date = checkValidation.checkValid.date1("Date(dd/mm/YYYY):");
            Available = checkValidation.checkValid.checkInt("Available Seat:", 0);
            initialAvailableSeats = Available;
            Flight f = new Flight(F_number, Departure_C, Destination_c, Departure_Time, Arrival_Time, Available, date, initialAvailableSeats);
            flights.put(F_number, f);
            System.out.println(f.getDeparture_Time());
            System.out.println("Add Flight succesfully!");
            Scanner sc = new Scanner(System.in);
            System.out.println("Do you want to add more?");
            Again = sc.nextLine();
        } while ("y".equalsIgnoreCase(Again) == true);
    }

    public void makeReservation() {

        if (flights.isEmpty()) {
            System.out.println("Don't have any flight!!!");
            return;
        }
        List<Flight> availableFlights = new ArrayList<>();
        String departureCity, destinationCity;
        String date;

        departureCity = checkValidation.checkValid.checkLength("Enter the departture city: ", 3, 50);

        destinationCity = checkValidation.checkValid.checkLength("Enter destination city: ", 3, 50);

        date = checkValidation.checkValid.date1("Enter date (dd/mm/yyyy):");

        /*    for (Flight flight : flights.values()) {
    if (flight.getDeparture_C().equalsIgnoreCase(departureCity)
            && flight.getDestination_c().equalsIgnoreCase(destinationCity)
            && flight.getDate().equals(date)
            && flight.getAvailable() > 0) {
        availableFlights.add(flight);
    }
   
}*/
        for (Flight flight : flights.values()) {
            /*   System.out.println("Departure City: " + flight.getDeparture_C());
            System.out.println("Destination City: " + flight.getDestination_c());
            System.out.println("Date: " + flight.getDate().toString());
            System.out.println("Available Seats: " + flight.getAvailable());*/

            if (flight.getDeparture_C().equalsIgnoreCase(departureCity)
                    && flight.getDestination_c().equalsIgnoreCase(destinationCity)
                    && flight.getDate().equals(date)
                    && flight.getAvailable() > 0) {
                availableFlights.add(flight);
            }
        }
        for (Flight flight : availableFlights) {
            System.out.printf("| %2s | %-8s | %10s | %-15s | %-14s |\n", flight.getF_number(),
                    flight.getDeparture_C(), flight.getDestination_c(), flight.getDeparture_Time(), flight.getArrival_Time(), flight.getDate());
        }

        String flightNumber = checkValidation.checkValid.InputPattern("Select a flight by entering its number:", "F[\\d]{4}").trim().toUpperCase();

        Flight flight;
        flight = searchFlightID(flightNumber);
        System.out.println("------------------------------------");
        if (flight == null) {
            System.out.println("The Flight does not exist");
        }

        String passengerName = checkValidation.checkValid.checkLength("Enter passenger name:", 3, 50);

        String contactDetails = checkValidation.checkValid.checkLength("Enter contact details:", 3, 50);

        // Generate unique reservation ID using UUID
        String reservationId = UUID.randomUUID().toString();

        Passenger passenger = new Passenger(passengerName, contactDetails);
        Reservation reservation = new Reservation(reservationId, passenger, flight, "Booked");
        reservations.put(reservationId, reservation);
      
        System.out.println("Booked!!!");
        System.out.println("RESERVATION ID:" + reservation.getReservationId());

        System.out.println("Reservation successfully made!");
    }

    public void checkIn() {
        // Prompt the passenger to enter their reservation ID
        String reservationId = checkValidation.checkValid.InputPattern("Enter your reservation ID: ", "[a-zA-Z0-9-]+");

        // Check if the reservation exists
        Reservation reservation;
        reservation = searchReservationtID(reservationId);
        System.out.println("------------------------------------");
        if (reservation == null) {
            System.out.println("The Flight does not exist");
        }

        // Get the flight associated with the reservation
        Flight flight = reservation.getFlight();
        if (flight == null) {
            System.out.println("Error: The flight associated with the reservation could not be found.");
            return;
        }
        displaySeatAvailability(flight.getF_number());

        // Prompt the user to select a seat
        /*    System.out.print("Select a seat number (1 to " + flight.getAvailable() + "): ");
int seatNumber = sc.nextInt();
sc.nextLine();

boolean isValidSeatNumber = false;
while (!isValidSeatNumber) {
    if (seatNumber < 1 || seatNumber > flight.getAvailable()) {
        System.out.println("Invalid seat number. Please select a seat from 1 to " + flight.getAvailable() + ".");
    } else {
        // Check if the seat number is already taken
        boolean isAlreadyTaken = false;
        for (Map.Entry<String, Reservation> entry : reservations.entrySet()) {
            Reservation p = entry.getValue();
            Passenger passenger = p.getPassenger();
            if (seatNumber == passenger.getSeat_number()) {
                isAlreadyTaken = true;
                break;
            }
        }
        if (isAlreadyTaken) {
            System.out.println("This seat is already taken. Please select another seat.");
        } else {
            isValidSeatNumber = true;
            flight.setAvailable(flight.getAvailable() - 1);
        }
    }
    if (!isValidSeatNumber) {
        // Prompt the user to select a seat again
        System.out.print("Select another seat number (1 to " + flight.getAvailable() + "): ");
        seatNumber = sc.nextInt();
        sc.nextLine();
    }
}

// Set the passenger's seat number
reservation.getPassenger().setSeat_number(seatNumber);*/
        // Prompt the user to select a seat
       List<Integer> occupiedSeats = new ArrayList<>();
        System.out.print("Select a seat number (1 to " + (flight.getAvailable() + occupiedSeats.size()) + " - Occupied Seats: ");
        
        for (Map.Entry<String, Reservation> entry : reservations.entrySet()) {
            Reservation p = entry.getValue();
            Passenger passenger = p.getPassenger();
            occupiedSeats.add(passenger.getSeat_number());
        }
        Collections.sort(occupiedSeats);
        for (Integer seat : occupiedSeats) {
            System.out.print(seat + " ");
        }
        System.out.println("): ");
        int seatNumber = sc.nextInt();
        sc.nextLine();

        boolean isValidSeatNumber = false;
        while (!isValidSeatNumber) {
            if (seatNumber < 1 || seatNumber > flight.getAvailable()) {
                System.out.println("Invalid seat number. Please select a seat from 1 to " + flight.getInitialAvailableSeats()+ ".");
            } else if (occupiedSeats.contains(seatNumber)) {
                System.out.println("This seat is already taken. Please select another seat.");
            } else {
                isValidSeatNumber = true;
                flight.setAvailable(flight.getAvailable() - 1);
            }
            if (!isValidSeatNumber) {
                // Prompt the user to select a seat again
                System.out.print("Select another seat number (1 to " + flight.getAvailable() + " - Occupied Seats: ");
                for (Integer seat : occupiedSeats) {
                    System.out.print(seat + " ");
                }
                System.out.println("): ");
                seatNumber = sc.nextInt();
                sc.nextLine();
            }
        }
        reservation.getPassenger().setSeat_number(seatNumber);
        // Generate the boarding pass with passenger and flight information
        String boardingPass = generateBoardingPass(reservation);
        System.out.println("Boarding Pass:");
        System.out.println(boardingPass);

    }

    public String generateBoardingPass(Reservation reservation) {
        StringBuilder boardingPass = new StringBuilder();
        boardingPass.append("Passenger: ").append(reservation.getPassenger().getName()).append("\n");
        boardingPass.append("Contact Details: ").append(reservation.getPassenger().getContact()).append("\n");
        boardingPass.append("Flight Number: ").append(reservation.getFlight().getF_number()).append("\n");
        boardingPass.append("Departure: ").append(reservation.getFlight().getDeparture_C()).append("\n");
        boardingPass.append("Destination: ").append(reservation.getFlight().getDestination_c()).append("\n");
        boardingPass.append("Date: ").append(reservation.getFlight().getDate()).append("\n");
        boardingPass.append("Seat Number: ").append(reservation.getPassenger().getSeat_number()).append("\n");
        return boardingPass.toString();
    }

    public void displaySeatAvailability(String flightNumber) {
        Flight flight = flights.get(flightNumber);
        if (flight == null) {
            System.out.println("Invalid flight number.");
            return;
        }

        int availableSeats = flight.getAvailable();
        System.out.println("Available seats for Flight " + flightNumber + ": " + availableSeats);
    }

    public void readfromfile1() {
        try {
            File file = new File(f1Name);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                String F_number = tokens[3].trim();
                String Departure_C = tokens[4].trim();

                String Destination_c = tokens[5].trim();
                LocalTime Departure_Time = LocalTime.parse(tokens[6].trim());
                LocalTime Arrival_Time = LocalTime.parse(tokens[7].trim());
                int Available = Integer.parseInt(tokens[8].trim());
                String date = tokens[9].trim();
                int InitialAvailableSeats = Integer.parseInt(tokens[10].trim());

                Flight n = new Flight(F_number, Departure_C, Destination_c, Departure_Time, Arrival_Time, Available, date, InitialAvailableSeats);
                flights.put(F_number, n);
            }
            scanner.close();
            System.out.println("Save!!!");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + e.getMessage());
        }
    }

    public void writefromfile2() throws IOException {
        try {
            FileWriter writer = new FileWriter(f1Name);

            /*   for (Map.Entry<String, Flight> entry : flights.entrySet()) {
                Flight p = entry.getValue();

                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime admissionTime = p.getArrival_Time();
                LocalTime admissionTime2= p.getDeparture_Time();
                String admissionTimeStr = admissionTime.format(timeFormatter);
                String line = p.getF_number() + ","
                        + p.getDeparture_C() + ","
                        + p.getDestination_c() + ","
                        + p.getDeparture_Time() + ","
                        + admissionTime2 + ","
                        + admissionTime + ","
                        
                        + p.getAvailable()+ ","
                        
                        + p.getDate() + "\n";

                writer.write(line);
            }*/
            for (Map.Entry<String, Reservation> entry : reservations.entrySet()) {
                Reservation p = entry.getValue();
                Passenger passenger = p.getPassenger();

                Flight flight = p.getFlight();

                String line = p.getReservationId() + ","
                        + passenger.getName() + ","
                        + passenger.getContact() + ","
                        + flight.getF_number() + ","
                        + flight.getDeparture_C() + ","
                        + flight.getDestination_c() + ","
                        + flight.getDeparture_Time() + ","
                        + flight.getArrival_Time() + ","
                        + flight.getAvailable() + ","
                        + flight.getDate() + ","
                        +flight.getInitialAvailableSeats()+ ","
                        + p.getStatus() + "\n";
                        
                writer.write(line);
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void displaySortedPatients() {
        Scanner sc = new Scanner(System.in);

        // Create a list of patients from the patient map
        List<Flight> FlightList = new ArrayList<>(flights.values());

        // Sort the patients based on the sorted field and sort order
        Collections.sort(FlightList, Comparator.comparing(Flight::getDate).reversed());

        // Display the sorted list of patients in a formatted way
        System.out.println("LIST OF PATIENTS");

        System.out.printf("%-3s %-10s %-15s %-20s %-12s %s\n", "No.", "Patient Id", "Admission Date", "Full name", "Phone", "Diagnosis");

        int count = 1;
        for (Flight flight : FlightList) {
            System.out.printf("| %2s | %-8s | %10s | %-15s | %-14s |\n", flight.getF_number(),
                    flight.getDeparture_C(), flight.getDestination_c(), flight.getDeparture_Time(), flight.getArrival_Time(), flight.getDate());
        }

    }
}
