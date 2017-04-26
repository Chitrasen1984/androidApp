package com.bravvura.gourmet.ui.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.bravvura.gourmet.R;
import com.bravvura.gourmet.models.CategoryBean;
import com.bravvura.gourmet.ui.adapters.CategoryExpandableListAdapter;
import com.bravvura.gourmet.ui.fragments.BaseBottomTabFragment;
import com.bravvura.gourmet.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.navigation_view_expandable_list_view)
    ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initToolbar();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //navigationView.setItemIconTintList(null);
        drawerLayout.addDrawerListener(toggle);
        drawerLayout.setStatusBarBackgroundColor(getResources().getColor(android.R.color.transparent));
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(false);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.mipmap.hamburger, getTheme());
        toggle.setHomeAsUpIndicator(drawable);

        /*NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/

        initDrawerView();

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
                init(Constants.TAG_CATEGORY_SCREEN);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true /* or true depending on what you need */;
            }
        };
        CategoryExpandableListAdapter categoryExpandableListAdapter = new CategoryExpandableListAdapter(this, prepareListData(), onChildClickListener);
        expandableListView.setAdapter(categoryExpandableListAdapter);
    }

    private void initToolbar() {
        toolbar.setLogo(R.mipmap.gourmet_logo);
        //toolbar.addView(LayoutInflater.from(this).inflate(R.layout.home_screen_toolbar_view, null));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void init(int screenTag) {
        Fragment baseTabfragment = new BaseBottomTabFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.EXTRA_LOAD_SCREEN, screenTag);
        baseTabfragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.app_bar_home_fl_container, baseTabfragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

   /* @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/

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
}
