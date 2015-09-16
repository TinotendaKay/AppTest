/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apptest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tinotenda
 */
public class AppTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
         Connection connection = getConnection("test");
         String query = "select * from user";
        try(  Statement createStatement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);ResultSet result = createStatement.executeQuery(query))
        {
           while(result.next())
           {
               String name = result.getString("user_name");
               String surname = result.getString("user_surname");
               
               System.out.println("First Name : "+name+" Surname : "+surname);
           }
          
            
        } catch (SQLException ex)
        {
            Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public static Connection getConnection(String databaseName)
    {
        
        Connection connection = null;
        Properties connProps = new Properties();
        connProps.put("user", "root");
        connProps.put("password", "password");
        String url = "jdbc:mysql://localhost:3306/"+databaseName+"";
        
        try 
        {
            connection = DriverManager.getConnection(url, connProps);
           // connection.close();
        } catch (SQLException e) 
        {
            System.out.println("\n\n************EXCEPTION CAUGHT***************\n\n");
            e.printStackTrace();
        }
      
      
        return connection;
    }
    
}
