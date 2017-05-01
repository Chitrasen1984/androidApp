package com.bravvura.gourmet.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bravvura.gourmet.R;
import com.bravvura.gourmet.listeners.OnProductRefreshListener;
import com.bravvura.gourmet.models.ProductCategory;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by munchado on 26/4/17.
 */

public class ProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<ProductCategory> productCategories;
    private OnProductRefreshListener onProductUpdateListener;

    public ProductListAdapter(Context context, ArrayList<ProductCategory> productCategories, OnProductRefreshListener onProductUpdateListener) {
        this.context = context;
        this.productCategories = productCategories;
        this.onProductUpdateListener = onProductUpdateListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        ProductCategory productCategory = productCategories.get(position);

        viewHolder.tvTitle.setText(productCategory.header);
        //onProductUpdateListener.updateProducts(viewHolder.rvProducts, productCategory.productBean);
    }

    @Override
    public int getItemCount() {
        return productCategories.size();
    }

    /*
    This class holds all the view objects for list
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.product_list_row_tv_heading)
        TextView tvTitle;

        /*@Bind(R.id.product_list_row_rv_product)
        RecyclerView rvProducts;*/

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }
}
