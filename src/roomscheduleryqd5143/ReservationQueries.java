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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

public class ReservationQueries {
    //getReservations
    //getRoomsReservedByDate
    //addReservationEntry
    //cancelReservation
    //getReservationsByFaculty
    //deleteReservation
    //methods^ for this class 

    private static Connection connection;
    private static PreparedStatement addReservation;
    private static PreparedStatement getReservation;
    private static PreparedStatement getReservationByDate;
    private static PreparedStatement cancelReservation;// user input
    private static PreparedStatement cancelReservationByRoom;// logic
    private static PreparedStatement getReservationByRoom;// logic
    private static PreparedStatement getSpecificReservationbydate;
    private static PreparedStatement getReservationByFaculty;
    private static ResultSet resultset;
    
    private static ArrayList<String> ReservationByDate = new ArrayList<String>();
    
    //dont forget to use timestamp code from canvas!!!
    
    public static void addReservation(String faculty,String room,Date date,int seats){
    connection = DBConnection.getConnection();
    java.sql.Timestamp ts = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());    
    try
        {
    addReservation = connection.prepareStatement("insert into reservation (faculty,room,date,seats,timestamp) values(?,?,?,?,?)");
           addReservation.setString(1,faculty);
           addReservation.setString(2, room);
           addReservation.setDate(3, date);
           addReservation.setInt(4, seats);
           addReservation.setTimestamp(5, ts);// double check because of option that came down
           addReservation.executeUpdate();
    }
    catch(SQLException ex){
    ex.printStackTrace();
    }
    
        }
    // for comparison
    public static ArrayList<String> getReservationsByDate(Date date){
    connection = DBConnection.getConnection();
    ArrayList<String> ReservationByDate = new ArrayList<String>(); 
    try{
    getReservationByDate = connection.prepareStatement("SELECT room from Reservation where date = (?) order by date,timestamp");
    getReservationByDate.setDate(1, date);
    resultset = getReservationByDate.executeQuery();
        
        
        while(resultset.next()){
        ReservationByDate.add(resultset.getString(1));
        }
    }
    catch(SQLException ex){
        ex.printStackTrace();
    }
    
    return ReservationByDate; // this should be a lit of faculy members this goes to status so objects to ouput
    }
   
// seperate method for output returns reservations on that date 
   public static ArrayList<ReservationEntry> ReservationsBydate(Date date){
       connection = DBConnection.getConnection();
       ArrayList<ReservationEntry> result = new ArrayList();
       try{
    getReservationByDate = connection.prepareStatement("SELECT * from Reservation where date = (?) order by date,timestamp");
    getReservationByDate.setDate(1, date);
    resultset = getReservationByDate.executeQuery();
        
        while(resultset.next()){
        result.add(new ReservationEntry(resultset.getString("FACULTY"),resultset.getString("ROOM"),resultset.getDate("DATE"),resultset.getInt("SEATS"),resultset.getTimestamp("TIMESTAMP")));
            
        }
    }
    catch(SQLException ex){
        ex.printStackTrace();
    }
       return result;
   }
   //final project 
   // prompted by user input
   public static void cancelReservation(String faculty, Date date){
    connection = DBConnection.getConnection();
        try
        {
            cancelReservation = connection.prepareStatement("delete from reservation where faculty = ? and date = ? ");
            cancelReservation.setString(1, faculty);
            cancelReservation.setDate(2, date);
            cancelReservation.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
   }
   
   // needed for drop room 
   public static void cancelReservationByRoom(String room){
   connection = DBConnection.getConnection();
        try
        {
            cancelReservationByRoom = connection.prepareStatement("delete from reservation where room = ?");
            cancelReservationByRoom.setString(1,room);
            cancelReservationByRoom.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
   }
   
   public static ArrayList<ReservationEntry> getReservationByRoom(String room){
   connection = DBConnection.getConnection();
     ArrayList<ReservationEntry> result = new ArrayList();
       try{
    getReservationByDate = connection.prepareStatement("SELECT * from Reservation where room = (?) order by date,timestamp");
    getReservationByDate.setString(1, room);
    resultset = getReservationByDate.executeQuery();
        
        while(resultset.next()){
        result.add(new ReservationEntry(resultset.getString("FACULTY"),resultset.getString("ROOM"),resultset.getDate("DATE"),resultset.getInt("SEATS"),resultset.getTimestamp("TIMESTAMP")));
            
        }
    }
    catch(SQLException ex){
        ex.printStackTrace();
    }
       return result;
   }
   
   public static ArrayList<ReservationEntry> getReservationByFaculty(String faculty){
   connection = DBConnection.getConnection();
    ArrayList<ReservationEntry> result = new ArrayList<>();
    try{
     getReservationByFaculty = connection.prepareStatement("select*from reservation where faculty =? order by date,timestamp");
       getReservationByFaculty.setString(1,faculty);
     resultset = getReservationByFaculty.executeQuery();
        while(resultset.next()){
       result.add(new ReservationEntry(resultset.getString("FACULTY"),resultset.getString("ROOM"),resultset.getDate("DATE"),resultset.getInt("SEATS"),resultset.getTimestamp("TIMESTAMP")));;
        }
     }
        catch(SQLException ex){
        ex.printStackTrace();
        }
        return result; 
   }  
}

