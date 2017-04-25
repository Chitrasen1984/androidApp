package com.bravvura.gourmet.utils;

/**
 * Created by Lenovo on 29-01-2017.
 */

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by naseeb on 10/6/2016.
 */

public class ScreenUtils {

    public static int getScreenWidth(Context context) {
        int screenWidth;

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;

        return screenWidth;
    }

    public static int getScreenHeight(Context context) {
        int screenHeight;

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenHeight = size.y;

        return screenHeight;
    }
}
