/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomscheduleryqd5143;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author yond1
 */
public class ReservationEntry {
    private String Faculty;
    private String Room;
    private Date Date;
    //^ primary keys
    private int seats;
    private Timestamp timestamp;

    public ReservationEntry(String Faculty, String Room, Date Date, int seats, Timestamp timestamp) {
        this.Faculty = Faculty;
        this.Room = Room;
        this.Date = Date;
        this.seats = seats;
        this.timestamp = timestamp;
    }

    public String getFaculty() {
        return Faculty;
    }

    public String getRoom() {
        return Room;
    }

    public Date getDate() {
        return Date;
    }

    public int getSeats() {
        return seats;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setFaculty(String Faculty) {
        this.Faculty = Faculty;
    }

    public void setRoom(String Room) {
        this.Room = Room;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
    
 }




