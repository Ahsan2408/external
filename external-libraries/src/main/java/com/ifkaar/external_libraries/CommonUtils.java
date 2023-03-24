package com.ifkaar.external_libraries;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.OpenableColumns;
import android.text.format.DateFormat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AlertDialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

/**
 * This class contains common utilities.
 *
 * @author Sajid Zeb
 * @version 1.0
 */
public class CommonUtils {
    private static final String TAG = "CommonUtils";

    /**
     * This method show alert dialog.
     *
     * @param context Context of calling class
     * @param message Message or text needs to show in alert
     */
    public static void showAlertDialog(Context context, String message) {
        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

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
     * This method hides keyboard.
     *
     * @param activity is calling activity.
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Check is Device connect with any network connection.
     *
     * @return Return true if connected else return false
     */
    public static boolean checkInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if ((connectivity != null) &&
                (connectivity.getActiveNetworkInfo() != null) &&
                (connectivity.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED)) {
            return true;
        }
        return false;
    }

    /**
     * Helper method which throws an exception  when an assertion has failed.
     */
    public static void assertIsTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    /**
     * Helper method for building a string of thread information.
     */
    public static String getThreadInfo() {
        return "@[name=" + Thread.currentThread().getName() + ", id=" + Thread.currentThread().getId()
                + "]";
    }

    /**
     * This method uses java.io.FileInputStream to read
     * file content into a byte array
     *
     * @param file
     * @return byteArray
     */
    public static byte[] covertFileToByteArray(File file) {
        FileInputStream fis = null;
        // Creating a byte array using the length of the file
        // file.length returns long which is cast to int
        byte[] byteArray = new byte[(int) file.length()];
        try {
            fis = new FileInputStream(file);
            fis.read(byteArray);
            fis.close();
        } catch (IOException ioExp) {
            ioExp.printStackTrace();
        }
        return byteArray;
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

    /**
     * This function return selected document name
     */
    @SuppressLint("Range")
    public static String getDocumentNameFromURI(Uri uri, Context context) {
        String res = null;
        if (uri.getScheme().equals("content")) {
            try (Cursor cursor = context.getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    res = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            }
            if (res == null) {
                res = uri.getPath();
                int cutt = res.lastIndexOf('/');
                if (cutt != -1) {
                    res = res.substring(cutt + 1);
                }
            }
        }
        return res;
    }
}
