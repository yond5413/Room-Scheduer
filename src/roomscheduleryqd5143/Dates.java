package roomscheduleryqd5143;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yond1
 */
public class Dates {
   private static Connection connection;
   private static ArrayList<String> date = new ArrayList<String>();
   private static PreparedStatement addDate;
   private static PreparedStatement getDateList;
   private static ResultSet resultSet;

   
    public static void addDate(Date date){
     connection = DBConnection.getConnection();
        try
        {
            addDate = connection.prepareStatement("insert into dates (date) values (?)");
            addDate.setDate(1, date);
            addDate.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
   public static ArrayList<String> getAllDates(){
    connection = DBConnection.getConnection();
     ArrayList<String> result = new ArrayList();
     SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");   
     try
        {
            getDateList = connection.prepareStatement("select distinct date from dates");
            resultSet = getDateList.executeQuery();
            
            while(resultSet.next())
            {
                result.add(formater.format(resultSet.getDate("Date")));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return result;}
    
    
    
    
    
}
