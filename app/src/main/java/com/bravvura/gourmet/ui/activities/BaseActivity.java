package com.bravvura.gourmet.ui.activities;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.bravvura.gourmet.BuildConfig;
import com.bravvura.gourmet.R;
import com.bravvura.gourmet.utils.Tracer;

/**
 * Created by munchado on 25/4/17.
 */

public class BaseActivity extends AppCompatActivity {

    private String TAG = BuildConfig.BASE_TAG + "." + "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
    }

    /**
     * Method to set the color of the status bar
     */
    @SuppressLint("NewApi")
    private void setStatusBarColor() {
        Tracer.debug(TAG, "NewActivityDashboard.setStatusBarColor() " + (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
          /*  window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);*/
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }
}
