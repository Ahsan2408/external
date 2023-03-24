package com.ifkaar.external_libraries;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences {
    private static final String TAG = "UserPreferences";

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static final String firstTimeRun = "FirstTimeRun";
    public static final String nullString = "null";
    public static final String userPrefs = "userPrefs";

    /**
     * This is default empty constructor.
     */
    public UserPreferences() {
    }

    /**
     * Initiallization
     */
    public static void init(Context applicationContext) {
        context = applicationContext;
    }

    /**
     * Gets the instance of SharedPreferences object
     *
     * @return SharedPreferences object
     */
    public static SharedPreferences getInstance(String prefName) {
        return context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }

    /**
     * Get the pref which is saved by setPrefValue.
     *
     * @param key Key to get value of pref
     * @return String value of current pref
     */
    public static String getPrefValue(String prefName, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(key, nullString);
        if (value.equalsIgnoreCase(nullString)) {
            value = null;
        }
        return value;
    }

    /**
     * Set the any user pref by passing key and its value.
     *
     * @param key   Key to save to pref
     * @param value Value of key of pref
     */
    public static void setPrefValue(String prefName, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (value == null) {
            value = nullString; // only Strings are allowed to put
        }
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Get the first time run state for app.
     */
    public static boolean isFirstTimeRun() {
        boolean result = false;
        String prefResult = getPrefValue(userPrefs, firstTimeRun);
        if (prefResult != null && prefResult.equals(firstTimeRun)) {
            result = true;
        }

        return result;
    }

    /**
     * Set the firt time run state for app.
     */
    public static void firstTimeRunCompleted() {
        setPrefValue(userPrefs, firstTimeRun, firstTimeRun);
    }
}
