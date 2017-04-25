package com.bravvura.gourmet.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bravvura.gourmet.R;
import com.bravvura.gourmet.models.BottomTabBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by munchado on 21/4/17.
 */

public class BaseBottomTabFragment extends Fragment {

    //@Bind(R.id.fragment_base_bottom_tab_tab_layout)
    TabLayout tabLayout;

    Fragment fragmentHome;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_bottom_tab, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = (TabLayout) view.findViewById(R.id.fragment_base_bottom_tab_tab_layout);
        setupTabLayout();
        setCustomViewOnTabLayout();
    }

    private void setupTabLayout() {
        fragmentHome = new HomeFragment();
        tabLayout.addTab(tabLayout.newTab(), true);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
    }

    private void setCustomViewOnTabLayout() {
        BottomTabBean[] tabArray = getBottomTabArray();
        for (int i = 0; i < tabArray.length; i++) {
            View tabView = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_tab_view, null);
            TextView tvTab = (TextView) tabView.findViewById(R.id.bottom_tab_view_tv);
            tvTab.setText(tabArray[i].tabText);

            tvTab.setCompoundDrawablesWithIntrinsicBounds(0, tabArray[i].tabImageId, 0, 0);
            tabLayout.getTabAt(i).setCustomView(tabView);
        }
        replaceFragment(fragmentHome);

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

    /*private void bindWidgetsWithAnEvent() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }*/

    /*private void setCurrentTabFragment(int tabPosition) {
        switch (tabPosition) {
            case 0:
                replaceFragment(fragmentHome);
                break;
            case 1:
                replaceFragment(fragmentHome);
                break;
        }
    }*/

    public void replaceFragment(Fragment fragment) {
        FragmentManager childFragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = childFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_base_bottom_tab_fl_container, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }
}
