package com.bravvura.gourmet.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.bravvura.gourmet.BuildConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by delhivery on 21/3/16.
 */
public class Tracer {
    private final static Boolean LOG_ENABLE = BuildConfig.SHOW_LOG;

    /**
     * Method to print debug log<br>
     * {@link Log} Information
     *
     * @param TAG
     * @param message
     */
    public static void debug(String TAG, String message) {
        if (LOG_ENABLE) {
            Log.d(TAG, message);
        }
    }

    /**
     * Method to print error log<br>
     * {@link Log} Error
     *
     * @param TAG
     * @param message
     */
    public static void error(String TAG, String message) {
        if (LOG_ENABLE) {
            Log.e(TAG, message);
        }
    }

    /**
     * Method to print information log<br>
     * {@link Log} Debug
     *
     * @param TAG
     * @param message
     */
    public static void info(String TAG, String message) {
        if (LOG_ENABLE) {
            Log.i(TAG, message);
        }
    }

    /**
     * Show Toast<br>
     *
     * @param TAG
     * @param message
     */
    public static void showToastUnderTesting(Context context, String TAG, String message) {
        if (LOG_ENABLE) {
            Toast toast = Toast.makeText(context, TAG + "\n" + message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    /**
     * Show Toast
     *
     * @param context
     * @param message
     * @param isLong  TRUE if show long toast else FALSE
     */
    public static void showToastProduction(Context context, String message, boolean isLong) {
        Toast toast = Toast.makeText(context, message, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
