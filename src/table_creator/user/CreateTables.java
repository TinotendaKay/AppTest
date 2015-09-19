/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table_creator.user;

import dao.user.UserDAO;
import java.sql.Connection;
import java.sql.Statement;
import lib.mysql.MySql;

/**
 *
 * @author tinotenda
 */
public class CreateTables
{

    private MySql mySql;

    public CreateTables(MySql mySql)
    {
        this.mySql = mySql;
    }

    public void createUserTable(String table)
    {
        try (Connection connection = mySql.getConnection(); Statement statement = connection.createStatement())
        {
            String createDB = "Create database if not exists " + UserDAO.DATABASE_NAME;

            statement.executeQuery(createDB);

            String createTable = "CREATE TABLE `user` (\n"
                    + " `user_id` bigint(20) NOT NULL AUTO_INCREMENT,\n"
                    + " `user_name` varchar(255) COLLATE latin2_hungarian_ci DEFAULT NULL,\n"
                    + " `user_surname` varchar(255) COLLATE latin2_hungarian_ci DEFAULT NULL,\n"
                    + " `created` timestamp NULL DEFAULT NULL,\n"
                    + " PRIMARY KEY (`user_id`)\n"
                    + ") ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=latin2 COLLATE=latin2_hungarian_ci";

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
