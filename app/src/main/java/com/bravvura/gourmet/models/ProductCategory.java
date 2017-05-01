package com.bravvura.gourmet.models;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bravvura.gourmet.R;
import com.bravvura.gourmet.listeners.OnProductRefreshListener;
import com.bravvura.gourmet.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by munchado on 26/4/17.
 */

public class ProductCategory extends StatelessSection {

    public String header;
    public List<ProductBean> productBean = new ArrayList<>();
    public Context context;
    public Fragment fragment;

    public ProductCategory(String title, List<ProductBean> list, Context context, Fragment fragment) {
        super(R.layout.product_list_row, R.layout.product_view_row);

        this.context = context;
        this.header = title;
        this.productBean = list;
        this.fragment = fragment;
    }

    @Override
    public int getContentItemsTotal() {
        return productBean.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemViewHolder itemHolder = (ItemViewHolder) holder;

        itemHolder.cardView.setLayoutParams(new RecyclerView.LayoutParams(ScreenUtils.getScreenWidth(context) / 2, LinearLayout.LayoutParams.WRAP_CONTENT));

        String name = productBean.get(position).productTitle;
        double price = productBean.get(position).productPrice;
        //String currency = productBean.get(position).currency;
        String quantity = productBean.get(position).quantity;

        itemHolder.tvItem.setText(name);
        itemHolder.tvPrice.setText(price + "");
        itemHolder.tvQuantity.setText(quantity);

        itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragment instanceof OnProductRefreshListener) {
                    ((OnProductRefreshListener) fragment).onClickProduct();
                }
              /*  Toast.makeText(getContext(), String.format("Clicked on position #%s of Section %s",
                        sectionAdapter.getPositionInSection(itemHolder.getAdapterPosition()), title),
                        Toast.LENGTH_SHORT).show();*/
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

        headerHolder.tvTitle.setText(header);

       /* headerHolder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), String.format("Clicked on more button from the header of Section %s",
                        title),
                        Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        //private final Button btnMore;

        public HeaderViewHolder(View view) {
            super(view);

            tvTitle = (TextView) view.findViewById(R.id.product_list_row_tv_heading);
            //btnMore = (Button) view.findViewById(R.id.btnMore);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private final View rootView;
        private final TextView tvItem;
        private final TextView tvQuantity;
        private final TextView tvPrice;

        public ItemViewHolder(View view) {
            super(view);

            rootView = view;
            cardView = (CardView) view.findViewById(R.id.product_view_row_card_view);
            tvItem = (TextView) view.findViewById(R.id.product_view_row_tv_product_title);
            tvQuantity = (TextView) view.findViewById(R.id.product_view_row_tv_product_quantity);
            tvPrice = (TextView) view.findViewById(R.id.product_view_row_tv_product_price);
        }
    }
}
