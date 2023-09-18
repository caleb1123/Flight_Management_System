/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;


import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author ryy15
 */
public class Flight {
    String F_number;
    String Departure_C;
    String Destination_c;
    LocalTime Departure_Time;
    LocalTime Arrival_Time ;
    int Available;
    String date;
    int initialAvailableSeats;

    public int getInitialAvailableSeats() {
        return initialAvailableSeats;
    }

    public void setInitialAvailableSeats(int initialAvailableSeats) {
        this.initialAvailableSeats = initialAvailableSeats;
    }
    public Flight() {
    }

    public Flight(String F_number, String Departure_C, String Destination_c, LocalTime Departure_Time, LocalTime Arrival_Time, int Available, String date, int initialAvailableSeats) {
        this.F_number = F_number;
        this.Departure_C = Departure_C;
        this.Destination_c = Destination_c;
        this.Departure_Time = Departure_Time;
        this.Arrival_Time = Arrival_Time;
        this.Available = Available;
        this.date = date;
        this.initialAvailableSeats = initialAvailableSeats;
    }

    public Flight(String F_number, String Departure_C, String Destination_c, LocalTime Departure_Time, LocalTime Arrival_Time, int Available, String date) {
        this.F_number = F_number;
        this.Departure_C = Departure_C;
        this.Destination_c = Destination_c;
        this.Departure_Time = Departure_Time;
        this.Arrival_Time = Arrival_Time;
        this.Available = Available;
        this.date = date;
    }

    public String getF_number() {
        return F_number;
    }

    public void setF_number(String F_number) {
        this.F_number = F_number;
    }

    public String getDeparture_C() {
        return Departure_C;
    }

    public void setDeparture_C(String Departure_C) {
        this.Departure_C = Departure_C;
    }

    public String getDestination_c() {
        return Destination_c;
    }

    public void setDestination_c(String Destination_c) {
        this.Destination_c = Destination_c;
    }

    public LocalTime getDeparture_Time() {
        return Departure_Time;
    }

    public void setDeparture_Time(LocalTime Departure_Time) {
        this.Departure_Time = Departure_Time;
    }

    public LocalTime getArrival_Time() {
        return Arrival_Time;
    }

    public void setArrival_Time(LocalTime Arrival_Time) {
        this.Arrival_Time = Arrival_Time;
    }

    public int getAvailable() {
        return Available;
    }

    public void setAvailable(int Available) {
        this.Available = Available;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

   

   

    
}
