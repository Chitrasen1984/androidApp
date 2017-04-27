package com.bravvura.gourmet.ui.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bravvura.gourmet.R;
import com.bravvura.gourmet.models.ProductBean;
import com.bravvura.gourmet.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by munchado on 26/4/17.
 */

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewAdapter.ViewHolder> {

    private Context context;
    private List<ProductBean> productBeans;

    public ProductViewAdapter(Context context, List<ProductBean> productBeans) {
        this.context = context;
        this.productBeans = productBeans;
    }

    @Override
    public ProductViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_view_row, parent, false);
        ProductViewAdapter.ViewHolder vh = new ProductViewAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ProductViewAdapter.ViewHolder holder, int position) {
        holder.cardView.setLayoutParams(new RecyclerView.LayoutParams(ScreenUtils.getScreenWidth(context) / 2, LinearLayout.LayoutParams.WRAP_CONTENT));
        //holder.ivProductImage.setLayoutParams(new LinearLayout.LayoutParams(ScreenUtils.getScreenWidth(context) / 2, ScreenUtils.getScreenWidth(context) / 2));

        ProductBean productBean = productBeans.get(position);

        holder.tvTitle.setText(productBean.productTitle);
        holder.tvQuantity.setText(productBean.quantity);
        holder.tvPrice.setText(productBean.productPrice + "");

    }

    @Override
    public int getItemCount() {
        return productBeans.size();
    }

    /*
    This class holds all the view objects for list
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.product_view_row_card_view)
        CardView cardView;

        @Bind(R.id.product_view_row_tv_product_title)
        TextView tvTitle;

        @Bind(R.id.product_view_row_tv_product_quantity)
        TextView tvQuantity;

        @Bind(R.id.product_view_row_tv_product_price)
        TextView tvPrice;

        @Bind(R.id.product_view_row_ll_container)
        LinearLayout linearLayoutContainer;

        @Bind(R.id.product_view_row_iv_product_image)
        ImageView ivProductImage;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }
}
