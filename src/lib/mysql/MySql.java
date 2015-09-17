/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author tinotenda
 */
public class MySql
{

    private final String databaseName;

    public MySql(String databaseName)
    {
        this.databaseName = databaseName;
    }

    public Connection getConnection()
    {

        Connection connection = null;
        Properties connProps = new Properties();
        connProps.put("user", "root");
        connProps.put("password", "password");
        String url = "jdbc:mysql://localhost:3306/" + this.databaseName + "";

        try
        {
            connection = DriverManager.getConnection(url, connProps);
            // connection.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return connection;
    }
}
