package com.bravvura.gourmet.ui.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bravvura.gourmet.R;
import com.bravvura.gourmet.models.CategoryBean;

/**
 * Created by munchado on 25/4/17.
 */

public class CategorySecondLevelAdapter extends BaseExpandableListAdapter {

    public CategoryBean child;
    private final LayoutInflater inflater;
    private Context context;

    public CategorySecondLevelAdapter(Context context, CategoryBean child) {
        this.child = child;
        this.inflater = LayoutInflater.from(context);
        this.context= context;
    }

    @Override
    public int getGroupCount() {
        return child.childCategoryBean.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child.childCategoryBean.get(groupPosition).childCategoryBean.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return child.childCategoryBean.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.childCategoryBean.get(groupPosition).childCategoryBean.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View layout = convertView;
        ViewHolder holder;

        final CategoryBean item = (CategoryBean) getGroup(groupPosition);

        if (layout == null) {
            layout = inflater.inflate(R.layout.child_category_child_row, parent, false);
            holder = new ViewHolder();
            holder.tvTitle = (TextView) layout.findViewById(R.id.category_child_row_tv_title);
            layout.setTag(holder);
        } else {
            holder = (ViewHolder) layout.getTag();
        }

        holder.tvTitle.setText(item.title.trim());

        return layout;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View layout = convertView;
        //final Object item = (Object) getChild(groupPosition, childPosition);
        final CategoryBean item = (CategoryBean) getChild(groupPosition, childPosition);

        ChildViewHolder holder;

        if (layout == null) {
            layout = inflater.inflate(R.layout.child_category_child_row, parent, false);

            holder = new ChildViewHolder();
            holder.flContainer = (FrameLayout) layout.findViewById(R.id.category_child_row_fl_container);
            holder.tvTitle = (TextView) layout.findViewById(R.id.category_child_row_tv_title);
            layout.setTag(holder);
        } else {
            holder = (ChildViewHolder) layout.getTag();
        }

        holder.tvTitle.setText(item.title.trim());
        //holder.tvTitle.setTextColor(context.getResources().getColor(R.color.green));
        holder.tvTitle.setTextColor(context.getResources().getColor(android.R.color.holo_green_dark));
        /*holder.flContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        return layout;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private static class ViewHolder {
        TextView tvTitle;
        ImageView ivExpand;
    }

    private static class ChildViewHolder {
        FrameLayout flContainer;
        TextView tvTitle;
        ImageView ivExpand;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        super.unregisterDataSetObserver(observer);
        if (observer != null) {
            super.unregisterDataSetObserver(observer);
        }
    }
}
