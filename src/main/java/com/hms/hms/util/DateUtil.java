package com.hms.hms.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
  
  public static long getStartOfTodayInMillis() {
        Calendar calendar = Calendar.getInstance();
        // Set hour, minute, second, and millisecond to 0 to get the start of today
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();  // Returns the timestamp in milliseconds
    }
    public static long getEndOfTodayInMillis() {
      Calendar calendar = Calendar.getInstance();
      // Set hour, minute, second, and millisecond to the last moment of the day
      calendar.set(Calendar.HOUR_OF_DAY, 23);
      calendar.set(Calendar.MINUTE, 59);
      calendar.set(Calendar.SECOND, 59);
      calendar.set(Calendar.MILLISECOND, 999);
      return calendar.getTimeInMillis();  // Returns the timestamp for the end of today
  }

  public static long convertDateToMillis(String dateString) {
        try {
            // Define the date format according to the user input (dd-MM-yyyy)
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            
            // Parse the date string to Date
            Date date = sdf.parse(dateString);
            
            // Return the timestamp in milliseconds
            return date.getTime();
        } catch (Exception e) {
            // Handle parsing exceptions
            e.printStackTrace();
            return -1; // Return an invalid value to indicate an error
        }
    }
  
}
