package com.bravvura.gourmet.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bravvura.gourmet.R;
import com.bravvura.gourmet.ui.adapters.HomeBannerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by munchado on 21/4/17.
 */

public class HomeFragment extends Fragment {

    @Bind(R.id.fragment_home_recycler_view_banner)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       /* if (getActivity() != null && getActivity() instanceof OnToolbarViewChangeListener) {
            ((OnToolbarViewChangeListener) getActivity()).onChangeToolbarView(Constants.TAG_HOME_SCREEN);
        }*/
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new HomeBannerAdapter(getActivity()));
    }

}
