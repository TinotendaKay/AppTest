/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user;

/**
 *
 * @author tinotenda
 */
public class User
{
    //set up field names
    public static final String FIELD_USER_NAME = "user_name";
    public static final String FIELD_SURNAME = "surname";
    public static final String FIELD_USER_ID = "user_id";
    public static final String FIELD_CREATED = "created";
        
    private String userName;
    private String surname;
    private long userId;
    private long created;

    public User(String userName, String surname)
    {
        this.userName = userName;
        this.surname = surname;
    }

    public User()
    {
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public long getUserId()
    {
        return userId;
    }

    public long getCreated()
    {
        return created;
    }

    public void setCreated(long created)
    {
        this.created = created;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
    }

    @Override
    public String toString()
    {
        return "User{" + "userName=" + ((this.userName != null) ? userName : "") + ", surname=" + ((this.surname != null) ? this.surname : "") + ", userId=" + ((this.userId != 0) ? this.userId : "") + ", created=" + ((this.created != 0) ? lib.util.DateTime.formartDate(this.created) : "") + '}';
    }

}
