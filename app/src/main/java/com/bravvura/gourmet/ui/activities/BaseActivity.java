package com.bravvura.gourmet.ui.activities;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bravvura.gourmet.BuildConfig;
import com.bravvura.gourmet.R;
import com.bravvura.gourmet.models.BottomTabBean;
import com.bravvura.gourmet.utils.Tracer;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by munchado on 25/4/17.
 */

public class BaseActivity extends AppCompatActivity {

    private String TAG = BuildConfig.BASE_TAG + "." + "BaseActivity";

    //@Bind(R.id.tab_layout)
    TabLayout tabLayout;

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

    public void setContentView(int layoutResId) {
        super.setContentView(R.layout.activity_base);
        //ButterKnife.bind(this);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        getLayoutInflater().inflate(layoutResId,
                (ViewGroup) findViewById(R.id.content));

        setupTabLayout();
        setCustomViewOnTabLayout();

    }

    private void setupTabLayout() {

        tabLayout.addTab(tabLayout.newTab(), true);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
    }

    private void setCustomViewOnTabLayout() {
        BottomTabBean[] tabArray = getBottomTabArray();
        for (int i = 0; i < tabArray.length; i++) {
            View tabView = LayoutInflater.from(this).inflate(R.layout.bottom_tab_view, null);
            TextView tvTab = (TextView) tabView.findViewById(R.id.bottom_tab_view_tv);
            tvTab.setText(tabArray[i].tabText);

            tvTab.setCompoundDrawablesWithIntrinsicBounds(0, tabArray[i].tabImageId, 0, 0);
            tabLayout.getTabAt(i).setCustomView(tabView);
        }
    }

    private BottomTabBean[] getBottomTabArray() {
        BottomTabBean Titles[] = new BottomTabBean[4];

        BottomTabBean bottomTabBean = new BottomTabBean();
        bottomTabBean.tabText = "HOME";
        bottomTabBean.tabImageId = R.mipmap.home_filled_dark_purple;
        Titles[0] = bottomTabBean;

        bottomTabBean = new BottomTabBean();
        bottomTabBean.tabText = "OFFERS";
        bottomTabBean.tabImageId = R.mipmap.timeline_filled_dark_purple;
        Titles[1] = bottomTabBean;

        bottomTabBean = new BottomTabBean();
        bottomTabBean.tabText = "RECIPES";
        bottomTabBean.tabImageId = R.mipmap.home_filled_dark_purple;
        Titles[2] = bottomTabBean;

        bottomTabBean = new BottomTabBean();
        bottomTabBean.tabText = "ACCOUNT";
        bottomTabBean.tabImageId = R.mipmap.timeline_filled_dark_purple;
        Titles[3] = bottomTabBean;

        return Titles;
    }
}
