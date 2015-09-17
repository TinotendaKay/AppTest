/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apptest;

import general.ListRequester;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import methods.user.UserDAOImplementation;
import model.user.User;

/**
 *
 * @author tinotenda
 */
public class AppTest
{

    private static final long MAX_FILE_SIZE  = (1024*1024)*5;// 5 megabytes
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        boolean readFromFile = false;
        boolean readFromArgs = false;
        
        switch (args.length)
        {
            case 0:
                System.out.println("Please specify startup arguments");
                System.exit(-1);// exits the applications
                break;
            case 1:
                readFromFile = true;
                break;
            case 2:
                readFromArgs = true;
                break;

            default:
                System.out.println("Invalid number of arguments.");
                System.out.println("Arborting..........");
                System.exit(-1);//exits the application
        }

        AppTest appTest = new AppTest();
        UserDAOImplementation userDAOImplementation = new UserDAOImplementation();

        if (readFromFile)
        {
            String filePath = args[0];
            appTest.readUserFromFile(filePath, userDAOImplementation);
        }

        if (readFromArgs)
        {
            appTest.readUserFromArguments(args, userDAOImplementation);
        }
        
        appTest.listUsers(userDAOImplementation);
    }

    /**
     * This methods reads user from a csv file as specified by the filePath parameter
     * Fist of the method checks if the file exists, if it exist it goes on to check if file is of
     * correct format and if it does not exceed maximum file size.
     * If satisfied with the above checks 
     * @param filePath = file location
     * @param imp 
     */
    private void readUserFromFile(String filePath, UserDAOImplementation imp)
    {
        Path source = Paths.get(filePath);
        
        boolean exists = Files.exists(source); // check if the file exist
        if(!exists)  
        {
            System.out.println("File "+source+"could not be found!");
            System.exit(-1);
        }
        
        String fileName = String.format("%s", source.getFileName());
        
        Pattern pattern = Pattern.compile("\\w.*\\.?csv$");// check if the file is in the right format
        Matcher matcher = pattern.matcher(fileName);
        if(matcher.find())
        {
            try
            {
                long fileSize = Files.size(source);
                if(fileSize>MAX_FILE_SIZE)
                {
                    Logger.getLogger(AppTest.class.getSimpleName()).log(Level.WARNING, "Max file size exceded");
                    System.out.println("Maximun file size must not be greater than 5 mega bytes");
                    
                }else// process the file
                {
                    List<String> lines = Files.readAllLines(source, Charset.defaultCharset());
                    Iterator<String> iterator = lines.iterator();
                    int count = 0;
                    while(iterator.hasNext())
                    {
                        String record = iterator.next();
                        String[] split = record.split(",", 2);
                        
                        AppTest appTest = new AppTest();
                        appTest.readUserFromArguments(split, imp);
                        count++;
                    }
                    
                    System.out.println("Successfully added "+count+ " user/s.");
                }
                
            } catch (IOException ex)
            {
               ex.printStackTrace();
            }
        } else{
            System.out.println("File "+fileName+" is not in the correct format, it must be a csv file");
        }
    
    }

    /**
     * Uses dao implementation object to add a new user to the database
     * @param args
     * @param imp 
     */
    private void readUserFromArguments(String[] args, UserDAOImplementation imp)
    {
        String name = args[0];
        String surname = args[1];
        User user = new User(name, surname);

        imp.addUser(user);
    }

    /**
     * Uses dao implementation object get a list of users and then prints them out.
     * @param imp 
     */
    private void listUsers(UserDAOImplementation imp)
    {
        ListRequester requester = new ListRequester();
        requester.setOrderBy(User.FIELD_CREATED);
        requester.setOrderDir((short)1);
        
        List<User> users = imp.getAllUsers(requester);
        if(users!=null&&!users.isEmpty())
        {
            Iterator<User> iterator = users.iterator();
            while (iterator.hasNext())
            {
                User user = iterator.next();
                System.out.println(user);
            }
        }
        
    }
}
