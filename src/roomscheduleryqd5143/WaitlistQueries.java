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

public class WaitlistQueries {
    
    private static Connection connection;
    private static PreparedStatement addWaitlist;
    private static PreparedStatement getWaitlist;
    private static PreparedStatement WaitListOutput;
    private static PreparedStatement cancelWaitlist;
    private static PreparedStatement getWaitListByDate;
    private static PreparedStatement getWaitlistbyFacutly;
    private static ArrayList<String> waitlist = new ArrayList<String>();
    private static ResultSet resultset;
    
    public static void addWaitlistEntry(String faculty,Date date, int seats){
    connection = DBConnection.getConnection();
       java.sql.Timestamp ts = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        try
        {
        addWaitlist = connection.prepareStatement("insert into waitlist(faculty,date,seats,timestamp) values(?,?,?,?)");
         addWaitlist.setString(1, faculty);
         addWaitlist.setDate(2, date);
         addWaitlist.setInt(3,seats);
         addWaitlist.setTimestamp(4, ts);//check this too same as in reservation queries
        addWaitlist.executeUpdate();
        }
        catch(SQLException ex){
        ex.printStackTrace();
        }
    }
    
    public static ArrayList<WaitlistEntry> getWaitlist(){
    connection = DBConnection.getConnection();
    ArrayList<WaitlistEntry> result = new ArrayList<>();
    try{
     WaitListOutput = connection.prepareStatement("select*from waitlist order by date, timestamp");
        resultset = WaitListOutput.executeQuery();
        while(resultset.next()){
        result.add(new WaitlistEntry(resultset.getString("FACULTY"),resultset.getDate("DATE"),resultset.getInt("SEATS"),resultset.getTimestamp("TIMESTAMP")));
        }
     }
        catch(SQLException ex){
        ex.printStackTrace();
        }
        return result; 
    }
//makes outputing easier^ 
  //final project
    public static void cancelWaitlist(String faculty, Date date){
// combo box string values > convert string data type back to date
    connection = DBConnection.getConnection();
        try
        {
            cancelWaitlist = connection.prepareStatement("delete from waitlist where faculty = ? and date = ?");
            cancelWaitlist.setString(1, faculty);
            cancelWaitlist.setDate(2, date);
            cancelWaitlist.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
   public static ArrayList<WaitlistEntry> getWaitlistByDate(Date date){
   connection = DBConnection.getConnection();
    ArrayList<WaitlistEntry> result = new ArrayList<>();
    try{
     getWaitListByDate = connection.prepareStatement("select*from waitlist where date =? order by date,timestamp");
       getWaitListByDate.setDate(1,date);
     resultset = getWaitListByDate.executeQuery();
        while(resultset.next()){
        result.add(new WaitlistEntry(resultset.getString("FACULTY"),resultset.getDate("DATE"),resultset.getInt("SEATS"),resultset.getTimestamp("TIMESTAMP")));
        }
     }
        catch(SQLException ex){
        ex.printStackTrace();
        }
        return result; 
   }
 public static ArrayList<WaitlistEntry> getWaitlistbyFacutly(String faculty){
  connection = DBConnection.getConnection();
    ArrayList<WaitlistEntry> result = new ArrayList<>();
    try{
     getWaitListByDate = connection.prepareStatement("select*from waitlist where faculty =? order by date,timestamp");
       getWaitListByDate.setString(1,faculty);
     resultset = getWaitListByDate.executeQuery();
        while(resultset.next()){
        result.add(new WaitlistEntry(resultset.getString("FACULTY"),resultset.getDate("DATE"),resultset.getInt("SEATS"),resultset.getTimestamp("TIMESTAMP")));
        }
     }
        catch(SQLException ex){
        ex.printStackTrace();
        }
        return result; 
 }
}



