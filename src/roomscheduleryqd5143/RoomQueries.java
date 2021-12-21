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
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoomQueries  {
    // 3 functions 
    //getAllPossibleRooms
    //addRoom
    //dropRoom
    private static Connection connection;
//phase 2        
// private static PreparedStatement addRoom;
//private static PreparedStatement dropRoom;
    private static PreparedStatement getAllPossibleRooms;
    private static PreparedStatement getAllRooms;
    private static PreparedStatement addRoom;
    private static PreparedStatement dropRoom;
    private static ResultSet resultset;
    private static ArrayList<String> rooms = new ArrayList<String>();
    

  
    // method returns arraylist of strings with rooms that can fit all the people  
   
    public static ArrayList<String> getAllPossibleRooms(int seats){
    connection = DBConnection.getConnection();
    ArrayList<String> rooms = new ArrayList<String>();
    
    try {
getAllPossibleRooms = connection.prepareStatement("SELECT name FROM ROOMS where seats >= ? order by seats asc");
    getAllPossibleRooms.setInt(1,seats);
    resultset = getAllPossibleRooms.executeQuery();
        
   while (resultset.next()){
        rooms.add(resultset.getString("NAME"));
        }
    }    
    catch(SQLException ex){
        ex.printStackTrace();
    }
  return rooms;
  }  
    
    public static ArrayList<String> getAllRooms(){
      connection = DBConnection.getConnection();
    ArrayList<String> rooms = new ArrayList<String>();
    try {
getAllRooms = connection.prepareStatement("SELECT*FROM ROOMS ");
    resultset = getAllRooms.executeQuery();
   while (resultset.next()){
        rooms.add(resultset.getString("NAME"));
        }
    }    
    catch(SQLException ex){
        ex.printStackTrace();
    }
  return rooms;
    }
 // final project 
    public static void addRoom(String name,int seats){
    connection = DBConnection.getConnection();
        try
        {
            addRoom = connection.prepareStatement("insert into rooms (name,seats) values(?,?)");
            addRoom.setString(1, name);
            addRoom.setInt(2, seats);
            addRoom.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static void dropRoom(String name){
    connection = DBConnection.getConnection();
        try
        {
            dropRoom = connection.prepareStatement("delete from rooms where (name) = (?)");
            dropRoom.setString(1, name);
            dropRoom.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
