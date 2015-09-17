/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methods.user;

import apptest.AppTest;
import dao.user.UserDAO;
import general.ListRequester;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.mysql.MySql;
import model.user.User;

/**
 *
 * @author tinotenda
 */
public class UserDAOImplementation implements UserDAO
{

    MySql mysql = new MySql(DATABASE_NAME);

    @Override
    /**
     * Inserts a user record into the database
     */
    public User addUser(User user)
    {
        

        long currTime = System.currentTimeMillis();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd h:m:s");

        String dateTime = simpleDateFormat.format(new Date(currTime));

        String sql = "INSERT INTO "
                + " user "
                + " (user_name, user_surname, created) "
                + " values('" + user.getUserName() + "', '" + user.getSurname() + "', '" + dateTime + "')";

        try (Connection connection = mysql.getConnection(); Statement statement = connection.createStatement())
        {
            int put = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            if (put > 0)
            {
                ResultSet result = statement.getGeneratedKeys();
                while (result.next())
                {
                    user.setUserId(result.getLong(1));

                    break;
                }

            }

            if (user.getUserId() == 0)
            {
                user = null;
            }

        } catch (Exception e)
        {
            e.printStackTrace();
            user = null;
        }

        return user;
    }

    @Override
    /**
     * Gets a list of users form the database and returns it
     */
    public List<User> getAllUsers(ListRequester listRequester)
    {
        List<User> list = null;
        Connection connection = this.mysql.getConnection();
        StringBuilder builder = new StringBuilder();
        builder.append(" SELECT ");
        builder.append(" * ");
        builder.append(" FROM ");
        builder.append(" user ");

        if (listRequester != null)
        {
            String orderBy = listRequester.getOrderBy();
            String groupBy = listRequester.getGroupBy();
            short orderDir = listRequester.getOrderDir();
            int limit = listRequester.getLimit();

            if (orderBy != null)
            {
                builder.append(" ORDER BY ").append(orderBy);
            }
            if (groupBy != null)
            {
                builder.append(" GROUP BY ").append(groupBy);
            }
            builder.append((orderDir == 1) ? " ASC " : " DESC ");
            if (limit > 0)
            {
                builder.append(" LIMIT ").append(limit);
            }

        }

        String query = builder.toString();

        try (Statement createStatement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); ResultSet result = createStatement.executeQuery(query))
        {
            while (result.next())
            {
                User user = new User();
                long userId = result.getLong("user_id");
                String name = result.getString("user_name");
                String surname = result.getString("user_surname");
                Timestamp timestamp = result.getTimestamp("created");

                if (userId > 0)
                {
                    user.setUserId(userId);
                }
                if (name != null)
                {
                    user.setUserName(name);
                }
                if (surname != null)
                {
                    user.setSurname(surname);
                }
                if (timestamp != null)
                {
                    long time = timestamp.getTime();
                    user.setCreated(time);
                }
                
                if(list==null) list = new ArrayList<>();
                list.add(user);

            }

        } catch (SQLException ex)
        {
            Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
}
