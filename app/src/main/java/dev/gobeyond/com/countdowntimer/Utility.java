package dev.gobeyond.com.countdowntimer;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by gusta_000 on 24/8/2016.
 */
public class Utility {

    private static final String PREFS_NAME = BuildConfig.APPLICATION_ID;

    public static final String KEY_COUNT_DOWN_MILLIS = "count_down_millis";
    public static final String KEY_LAST_TIME_MILLIS = "last_time_millis";

    public static String updateCurrentCountDownTime(Context ctx) {
        long defaultValue = 3 * 60 * 1000; //3 minutes
        long countDownMillis = Utility.getLong(ctx, Utility.KEY_COUNT_DOWN_MILLIS, defaultValue);

        long currentTimeMillis = System.currentTimeMillis();
        long lastTimeMillis = Utility.getLong(ctx, Utility.KEY_LAST_TIME_MILLIS, currentTimeMillis);

        long difference = currentTimeMillis - lastTimeMillis;

        //Decrease the counter by the difference in time between this call and the last one
        countDownMillis -= difference;

        String hms;
        if(countDownMillis >= 0) {
            Utility.putLong(ctx, Utility.KEY_COUNT_DOWN_MILLIS, countDownMillis);
            Utility.putLong(ctx, Utility.KEY_LAST_TIME_MILLIS, currentTimeMillis);

            hms = String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(countDownMillis),
                    TimeUnit.MILLISECONDS.toMinutes(countDownMillis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(countDownMillis)),
                    TimeUnit.MILLISECONDS.toSeconds(countDownMillis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(countDownMillis)));
        }
        else {
            hms = "Completed!";
        }

        return hms;
    }

    public static void putLong(Context ctx, String key, long value) {
        SharedPreferences settings = ctx.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static long getLong(Context ctx, String key, long defaultValue) {
        SharedPreferences settings = ctx.getSharedPreferences(PREFS_NAME, 0);
        long value = settings.getLong(key, defaultValue);
        return value;
    }

}
