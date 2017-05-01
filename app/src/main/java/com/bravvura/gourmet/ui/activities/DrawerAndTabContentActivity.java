package com.bravvura.gourmet.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bravvura.gourmet.R;
import com.bravvura.gourmet.ui.fragments.CategoryFragment;
import com.bravvura.gourmet.utils.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by munchado on 1/5/17.
 */

public class DrawerAndTabContentActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    /*@Bind(R.id.toolbar_item_container)
    LinearLayout toolbarLinearLayout;*/

    int screenTAG = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);

        initToolbar();

        initView();
    }

    private void initToolbar() {
        //toolbar.setLogo(R.mipmap.gourmet_logo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //initiateToolbarView();
    }

    /*private void initiateToolbarView() {
        View toolbarView = LayoutInflater.from(this).inflate(R.layout.toolbar_view_category_screen, null);
        ImageView ivBack = (ImageView) toolbarView.findViewById(R.id.toolbar_view_category_screen_iv_back);
        ivBack.setOnClickListener(this);

        toolbarLinearLayout.addView(toolbarView);
    }*/

    private void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            screenTAG = intent.getIntExtra(Constants.EXTRA_SCREEN_TAG, 2);
        }

        if (screenTAG == Constants.TAG_CATEGORY_SCREEN) {
            Fragment fragmentCategory = new CategoryFragment();
            replaceFragment(fragmentCategory);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.toolbar_view_category_screen_iv_back) {
            onBackPressed();
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_tab_fl_container, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }
}
