/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author tinotenda
 */
public class DateTime
{
    public static String formartDate(long unixTimeStamp)
    {
        String pattern = "YYYY-MM-dd hh:mm:ss";

        return new SimpleDateFormat(pattern).format(new Date(unixTimeStamp));
    }
}
