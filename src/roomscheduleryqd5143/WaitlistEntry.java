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
public class WaitlistEntry {

    public String getFaculty() {
        return Faculty;
    }

    public Date getDate() {
        return date;
    }

    public int getSeats() {
        return Seats;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
    private String Faculty;
    private Date date;
    private int Seats;
    private Timestamp timestamp;

    public WaitlistEntry(String Faculty, Date date, int Seats, Timestamp timestamp) {
        this.Faculty = Faculty;
        this.date = date;
        this.Seats = Seats;
        this.timestamp = timestamp;
    }

    public void setFaculty(String Faculty) {
        this.Faculty = Faculty;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSeats(int Seats) {
        this.Seats = Seats;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
}
