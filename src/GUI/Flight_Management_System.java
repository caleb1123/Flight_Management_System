/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Core.FlightDAO;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author ryy15
 */
public class Flight_Management_System {

   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String pchoice, choice1, choice2;
            String pYN = "";
            boolean con = false;
            FlightDAO  dao = new FlightDAO();
        do {
            Scanner sc = new Scanner(System.in);
            
            System.out.println("1.Flight schedule management ");
            System.out.println("2.Passenger reservation and booking\n"
                    + "3.Passenger check-in and seat allocation\n"
                    + "4.Crew management and assignments\n"
                    + "5.Administrator access for system management\n"
                    + "6.Data storage for flight details, reservations, and assignments\n"
                    + "7.Quit");

            pchoice = sc.nextLine();
            switch (pchoice) {
                case "1":
                    dao.Add_Flight();
                    break;
                case "2":
                    dao.makeReservation();
                    break;



                case "3":
                    dao.checkIn();
                    break;
                case "4":
                    
                    
                    break;
                case "5":
                    dao.writefromfile2();
                    break;
                case "6":
                    dao.readfromfile1();
                            
                    dao.displaySortedPatients();
                    break;
                case "7":
                    System.exit(0);
                    break;
                default:
                    System.out.println("This is not a valid Menu Option! Please Select Another");
                    break;
            }
            do {
                System.out.print("Do you want to go back to the main menu (Y|N): ");
                sc = new Scanner(System.in);
                pYN = sc.nextLine();
                System.out.print("\n");
                con = !("Y".equals(pYN) || "N".equals(pYN)
                        || "y".equals(pYN) || "n".equals(pYN));
            } while (con);
         } while ("Y".equals(pYN) || "y".equals(pYN));
    }
}


