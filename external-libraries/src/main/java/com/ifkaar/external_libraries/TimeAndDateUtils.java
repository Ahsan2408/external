package com.ifkaar.external_libraries;

import android.os.Build;
import android.text.format.DateFormat;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class TimeAndDateUtils {
    private static final String TAG = "TimeAndDateUtils";

    /**
     * This method calculates date differences from two dates.
     *
     * @param date1 is date one.
     * @param date2 is date two.
     * @return date difference.
     */
    public static Map<TimeUnit, Long> computeDateDifference(Date date1, Date date2) {
        long diffInMillies = date2.getTime() - date1.getTime();
        List<TimeUnit> units = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class));
        Collections.reverse(units);

        Map<TimeUnit, Long> result = new LinkedHashMap<TimeUnit, Long>();
        long milliesRest = diffInMillies;
        for (TimeUnit unit : units) {
            long diff = unit.convert(milliesRest, TimeUnit.MILLISECONDS);
            long diffInMilliesForUnit = unit.toMillis(diff);
            milliesRest = milliesRest - diffInMilliesForUnit;
            result.put(unit, diff);
        }
        return result;
    }

    /**
     * This method converts milli seconds to time.
     *
     * @param milliSeconds is a long type string.
     * @return timerString.
     */
    public static String milliSecondToTimer(long milliSeconds) {
        String timerString = "";
        String secondsString;

        int hours = (int) (milliSeconds / (1000 * 60 * 60));
        int minutes = (int) (milliSeconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliSeconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        if (hours > 0) {
            timerString = hours + ":";
        }

        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        timerString = timerString + minutes + ":" + secondsString;
        return timerString;
    }

    /**
     * This method uses java.time library to get current date time
     *
     * @return zonedDateTime
     */
    public static String getUTCTimeNow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return String.valueOf(ZonedDateTime.now(ZoneOffset.UTC));
        } else { // For Below API 26
            return String.valueOf(org.threeten.bp.ZonedDateTime.now(org.threeten.bp.ZoneOffset.UTC));
        }
    }

    public static long getCurrentEpocTimeStampInMillis() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis()); // 2016-11-16 06:55:40.11

        //return number of milliseconds since January 1, 1970, 00:00:00 GMT
//        Log.d(timestamp.getTime()); // 1479250540110

//        // Convert timestamp to instant
//        Instant instant = timestamp.toInstant();
//        Log.d(instant); // 2016-11-15T22:55:40.110Z
//
//        //return number of milliseconds since the epoch of 1970-01-01T00:00:00Z
//        Log.d(instant.toEpochMilli()); // 1479250540110
//
//        // Convert instant to timestamp
//        Timestamp tsFromInstant = Timestamp.from(instant);
//        Log.d(tsFromInstant.getTime()); //1479250540110

//        Log.d(TAG, "timestamp.getTime(): " + timestamp.getTime());
        return timestamp.getTime();
    }

    public static Date getDateTimeDayWithTimeZone(long time) {
        TimeZone timeZone = Calendar.getInstance().getTimeZone();     // get your local time zone.
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        simpleDateFormat.setTimeZone(timeZone);//set time zone.
        String localTime = simpleDateFormat.format(new Date(time));
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(localTime);//get local date
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getDateTimeFromEpoch(String timeDate) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(Long.parseLong(timeDate));
        return DateFormat.format("d MMM yyyy, hh:mma", cal).toString();
//        return DateFormat.format("EEE, d MMM yyyy, hh:mma", cal).toString();
//        return DateFormat.format("dd-MM-yyyy hh:mm a", cal).toString();
    }

    public static String getDateFromEpoch(String timeDate) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(Long.parseLong(timeDate));
        return DateFormat.format("d MMM yyyy", cal).toString();
//        return DateFormat.format("EEE, d MMM yyyy, hh:mma", cal).toString();
//        return DateFormat.format("dd-MM-yyyy hh:mm a", cal).toString();
    }

    public static String getDateFromMilliseconds(String Date) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(Long.parseLong(Date));
        return DateFormat.format("dd/MM/yyyy", cal).toString();
    }

    public static String getPreviousDate(String selectedDate) {
        String toReturn = "";
        Date fromDate = new Date(selectedDate); //        Sun May 02 00:00:00 GMT+05:00 2021
        int MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
        Date toDate = new Date(fromDate.getTime() - MILLIS_IN_A_DAY);
        Long time = toDate.getTime();
        toReturn = getDateFromEpoch(time.toString()); // 2 May 2021
        return toReturn;
    }

    public static String getNextDate(String selectedDate) {
        String toReturn = "";
        Date fromDate = new Date(selectedDate); //        Sun May 02 00:00:00 GMT+05:00 2021
        int MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
        Date toDate = new Date(fromDate.getTime() + MILLIS_IN_A_DAY);
        Long time = toDate.getTime();
        toReturn = getDateFromEpoch(time.toString()); // 2 May 2021
        return toReturn;
    }

    public static String getNextDateInEpoch(String selectedDate) {
        String toReturn = "";
        Date fromDate = new Date(selectedDate); //        Sun May 02 00:00:00 GMT+05:00 2021
        int MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
        Date toDate = new Date(fromDate.getTime() + MILLIS_IN_A_DAY);
        Long time = toDate.getTime();
        toReturn = time.toString(); // 1609711699902
        return toReturn;
    }

    public static String getTodaysDate() {
        String toReturn = "";
        String currentTimeStampString = String.valueOf(getCurrentEpocTimeStampInMillis()); // 1609711699902
        toReturn = getDateFromEpoch(currentTimeStampString); // 2 May 2021
        return toReturn;
    }

    public static String getEpochMillisFromDate(String date) { // Input Date Format -> 04/28/2016
        String toReturn = "";
        try {
            Long millis = new SimpleDateFormat("dd/MM/yyyy").parse(date).getTime();
            toReturn = millis.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return toReturn;
    }
}
