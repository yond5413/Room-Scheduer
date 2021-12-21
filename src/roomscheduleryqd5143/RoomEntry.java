/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomscheduleryqd5143;

/**
 *
 * @author yond1
 */
public class RoomEntry {
    private String Name;//primary key in database
    private int seats;

    public RoomEntry(String Name, int seats) {
        this.Name = Name;
        this.seats = seats;
    }

    public String getName() {
        return Name;
    }

    public int getSeats() {
        return seats;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
    
   
}
