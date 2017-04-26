package com.bravvura.gourmet.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bravvura.gourmet.R;
import com.bravvura.gourmet.models.CategoryBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by munchado on 26/4/17.
 */

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<CategoryBean> categoryBeanList;

    public CategoryListAdapter(Context context, ArrayList<CategoryBean> categoryBeenList) {
        this.context = context;
        categoryBeanList = categoryBeenList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CategoryBean categoryBean = categoryBeanList.get(position);

        holder.tvTitle.setText(categoryBean.title);

    }

    @Override
    public int getItemCount() {
        return categoryBeanList.size();
    }

    /*
    This class holds all the view objects for list
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.category_row_tv_title)
        TextView tvTitle;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }
}
