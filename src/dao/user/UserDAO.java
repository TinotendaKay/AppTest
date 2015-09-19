/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.user;

import general.ListRequester;
import java.util.List;
import model.user.User;

/**
 *
 * @author tinotenda
 */
public interface UserDAO
{

    String DATABASE_NAME = "test";
    String TABLE_NAME = "user";

    User addUser(User user);
    List<User> getAllUsers(ListRequester listRequester);
}
