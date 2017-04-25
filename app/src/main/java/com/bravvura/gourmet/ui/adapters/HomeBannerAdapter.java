package com.bravvura.gourmet.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bravvura.gourmet.R;
import com.bravvura.gourmet.utils.ScreenUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by munchado on 21/4/17.
 */

public class HomeBannerAdapter extends RecyclerView.Adapter<HomeBannerAdapter.ItemViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public HomeBannerAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.home_banner_adapter_row_type_1, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        //holder.ivBanner.set
        holder.ivBanner.setLayoutParams(new FrameLayout.LayoutParams(ScreenUtils.getScreenWidth(mContext), FrameLayout.LayoutParams.MATCH_PARENT));

        if (position == 0) {
            holder.llRight.setVisibility(View.VISIBLE);
            holder.llCenter.setVisibility(View.GONE);
        } else {
            holder.llRight.setVisibility(View.GONE);
            holder.llCenter.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.home_banner_adapter_row_ll_center)
        LinearLayout llCenter;

        @Bind(R.id.home_banner_adapter_row_ll_right)
        LinearLayout llRight;

        @Bind(R.id.home_banner_adapter_row_iv_banner)
        ImageView ivBanner;

        public ItemViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
        }
    }
}
