package com.bravvura.gourmet.ui.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bravvura.gourmet.BuildConfig;
import com.bravvura.gourmet.R;
import com.bravvura.gourmet.models.CategoryBean;
import com.bravvura.gourmet.ui.adapters.CategoryExpandableListAdapter;
import com.bravvura.gourmet.ui.fragments.HomeFragment;
import com.bravvura.gourmet.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    /*@Bind(R.id.toolbar_item_container)
    LinearLayout toolbarLinearLayout;*/

    @Bind(R.id.navigation_view_expandable_list_view)
    ExpandableListView expandableListView;

    private ActionBarDrawerToggle toggle;

    private String TAG = BuildConfig.BASE_TAG + "." + "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initToolbar();

        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //navigationView.setItemIconTintList(null);
        drawerLayout.addDrawerListener(toggle);
        drawerLayout.setStatusBarBackgroundColor(getResources().getColor(android.R.color.transparent));
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(false);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.mipmap.hamburger, getTheme());
        toggle.setHomeAsUpIndicator(drawable);

        initDrawerView();
       /* setupTabLayout();
        setCustomViewOnTabLayout();*/

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        init(Constants.TAG_HOME_SCREEN);
    }

    private void initDrawerView() {

        // Third (and last) level items in the ExpandableListView
        ExpandableListView.OnChildClickListener onChildClickListener = new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView eListView, View view, int groupPosition,
                                        int childPosition, long id) {
                //init(Constants.TAG_CATEGORY_SCREEN);

                Intent intent = new Intent(HomeActivity.this, DrawerAndTabContentActivity.class);
                intent.putExtra(Constants.EXTRA_SCREEN_TAG, Constants.TAG_CATEGORY_SCREEN);
                startActivity(intent);

                drawerLayout.closeDrawer(GravityCompat.START);
                return true /* or true depending on what you need */;
            }
        };
        CategoryExpandableListAdapter categoryExpandableListAdapter = new CategoryExpandableListAdapter(this, prepareListData(), onChildClickListener);
        expandableListView.setAdapter(categoryExpandableListAdapter);
    }

    private void initToolbar() {
        toolbar.setLogo(R.mipmap.gourmet_logo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    /*private void initiateToolbarView(int screenTag) {
        toolbarLinearLayout.removeAllViews();
        View toolbarView = null;
        if (screenTag == Constants.TAG_HOME_SCREEN) {
            toolbarView = LayoutInflater.from(this).inflate(R.layout.toolbar_view_home_screen, null);
           *//* RelativeLayout outerLayout = (RelativeLayout) toolbarView.findViewById(R.id.toolbar_view_home_screen_rl);
            outerLayout.setLayoutParams(new FrameLayout.LayoutParams(toolbar.getWidth(), toolbar.getHeight()));*//*
            ImageView ivHamburger = (ImageView) toolbarView.findViewById(R.id.toolbar_view_home_screen_iv_hamburger);
            ivHamburger.setOnClickListener(this);
        } *//*else if (screenTag == Constants.TAG_CATEGORY_SCREEN) {
            toolbarView = LayoutInflater.from(this).inflate(R.layout.toolbar_view_category_screen, null);
            ImageView ivBack = (ImageView) toolbarView.findViewById(R.id.toolbar_view_category_screen_iv_back);
            ivBack.setOnClickListener(this);
        }*//*
        toolbarLinearLayout.addView(toolbarView);
    }*/

    private void init(int screenTag) {
        /*Fragment baseTabfragment = new BaseBottomTabFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.EXTRA_LOAD_SCREEN, screenTag);
        baseTabfragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.app_bar_home_fl_container, baseTabfragment).commit();*/

        //initiateToolbarView(screenTag);
        if (screenTag == Constants.TAG_HOME_SCREEN) {
            Fragment fragmentHome = new HomeFragment();
            replaceFragment(fragmentHome);
        }
        /*else if (screenTag == Constants.TAG_CATEGORY_SCREEN) {
            Fragment fragmentCategory = new CategoryFragment();
            replaceFragment(fragmentCategory);
        }*/
    }

    @Override
    public void onBackPressed() {
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            /*Tracer.info(TAG, "Back Stack entry count " + getSupportFragmentManager().getBackStackEntryCount());
            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                //getSupportFragmentManager().popBackStack();
                super.onBackPressed();
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.app_bar_home_fl_container);
                if (fragment instanceof HomeFragment) {
                    initiateToolbarView(Constants.TAG_HOME_SCREEN);
                }
            } else {
                finish();
            }*/
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.app_bar_home_fl_container, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null).commit();
    }

    private ArrayList<CategoryBean> prepareListData() {

        ArrayList<CategoryBean> categoryBeanArrayList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            CategoryBean categoryBean = new CategoryBean();
            if (i == 0)
                categoryBean.title = "Our Picks";
            else if (i == 1)
                categoryBean.title = "Quick & Easy Food Solutions";
            else if (i == 2)
                categoryBean.title = "Meat & poultry";
            else if (i == 3)
                categoryBean.title = "Seafood";
            List<CategoryBean> childCategoryBeanList = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                CategoryBean childCategoryBean = new CategoryBean();
                if (j == 0)
                    childCategoryBean.title = "READY TO EAT";
                else if (j == 1)
                    childCategoryBean.title = "READY TO HEAT";
                List<CategoryBean> childLevel2categoryBeanList = new ArrayList<>();
                for (int k = 0; k < 4; k++) {
                    CategoryBean childLevel2CategoryBean = new CategoryBean();
                    if (k == 0)
                        childLevel2CategoryBean.title = "Vegan/Vegetarian";
                    else if (k == 1)
                        childLevel2CategoryBean.title = "Appetizers";
                    else if (k == 2)
                        childLevel2CategoryBean.title = "Sides";
                    else if (k == 3)
                        childLevel2CategoryBean.title = "Mains";
                    childLevel2categoryBeanList.add(childLevel2CategoryBean);
                }
                childCategoryBean.childCategoryBean = childLevel2categoryBeanList;
                childCategoryBeanList.add(childCategoryBean);
            }
            categoryBean.childCategoryBean = childCategoryBeanList;
            categoryBeanArrayList.add(categoryBean);
        }
        return categoryBeanArrayList;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.toolbar_view_home_screen_iv_hamburger) {
            if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        }
    }

}
