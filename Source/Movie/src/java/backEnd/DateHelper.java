/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package backEnd;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Properties;

/**
 *
 * @author ido
 */
public class DateHelper 
{
    int  MAX_RENT_DAYS=0;
    int  MAX_RENT_HOURS=0;
    int  MAX_RENT_MINUTES=0;
    
    public DateHelper() 
    {
        Properties properties = new Properties();
        
        try 
        {
            properties.load(new FileInputStream("c:\\movieSystem.properties"));
        }
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    
        MAX_RENT_DAYS = Integer.parseInt(properties.getProperty("MAX_RENT_DAYS"));
        MAX_RENT_HOURS = Integer.parseInt(properties.getProperty("MAX_RENT_HOURS"));
        MAX_RENT_MINUTES = Integer.parseInt(properties.getProperty("MAX_RENT_MINUTES"));
    }
        

    public String getReturnTime(long rentTime)
    {
        GregorianCalendar gc = new GregorianCalendar();
		
        gc.setTimeInMillis(rentTime); 
		
        gc.add(GregorianCalendar.DAY_OF_YEAR, MAX_RENT_DAYS);          
        gc.add(GregorianCalendar.HOUR_OF_DAY, MAX_RENT_HOURS);
        gc.add(GregorianCalendar.MINUTE, MAX_RENT_MINUTES);

        
        /* formatting with Locale.UK means that the
           day of month  comes before the month . */
        DateFormat dateFormat =
        DateFormat.getDateTimeInstance(
                    DateFormat.SHORT,
                    DateFormat.MEDIUM, Locale.UK);
            
            
        // gets the formatted date and time
        String returnTime = dateFormat.format(gc.getTime());            
           
        return returnTime; 
    
    }         
        
    
    public boolean rentedCopyDurationElapsed(long rentTime)
    {
        GregorianCalendar gc = new GregorianCalendar();
		
        gc.setTimeInMillis(rentTime); 
            
        gc.add(GregorianCalendar.DAY_OF_YEAR, MAX_RENT_DAYS);          
        gc.add(GregorianCalendar.HOUR_OF_DAY, MAX_RENT_HOURS);
        gc.add(GregorianCalendar.MINUTE, MAX_RENT_MINUTES);
            
        // number of milliseconds since 1970 till the return date
        long returnTimeInMillis = gc.getTimeInMillis();
            
        java.util.Date currentDate = new java.util.Date();
        gc.setTime(currentDate);
            
        // number of milliseconds since 1970 till now
        long currentTimeInMillis = gc.getTimeInMillis();
            
        // true if copy rent duration elapsed
        if (currentTimeInMillis > returnTimeInMillis)
        {
            return true;   
        }
            
        return false;
        
    }         
    
}
