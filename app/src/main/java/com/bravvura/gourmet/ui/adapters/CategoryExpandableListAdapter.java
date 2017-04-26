package com.bravvura.gourmet.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bravvura.gourmet.R;
import com.bravvura.gourmet.models.CategoryBean;
import com.bravvura.gourmet.ui.widgets.CustomExpandableListView;

import java.util.ArrayList;

/**
 * Created by munchado on 21/4/17.
 */

public class CategoryExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private ArrayList<CategoryBean> categoryBeanList;
    private final LayoutInflater inflater;
    public Entry[] lsfirst;
    private ExpandableListView.OnChildClickListener onChildClickListener;

    public class Entry {
        public final CustomExpandableListView cls;
        public final CategorySecondLevelAdapter sadpt;

        public Entry(CustomExpandableListView cls, CategorySecondLevelAdapter sadpt) {
            this.cls = cls;
            this.sadpt = sadpt;
        }
    }

    public CategoryExpandableListAdapter(Context context, ArrayList<CategoryBean> categoryBean, ExpandableListView.OnChildClickListener onChildClickListener) {
        mContext = context;
        categoryBeanList = categoryBean;
        this.inflater = LayoutInflater.from(context);
        this.onChildClickListener = onChildClickListener;

        lsfirst = new Entry[categoryBeanList.size()];

        for (int i = 0; i < categoryBeanList.size(); i++) {
            final CustomExpandableListView celv = new CustomExpandableListView(context);
            CategorySecondLevelAdapter adp = new CategorySecondLevelAdapter(context, categoryBeanList.get(i));
            celv.setAdapter(adp);
            celv.setGroupIndicator(null);
            celv.setDivider(null);
            celv.setOnChildClickListener(onChildClickListener);
            /*celv.setOnGroupClickListener(grpLst);
            celv.setOnGroupExpandListener(grpExpLst);*/

            lsfirst[i] = new Entry(celv, adp);
        }
    }

    @Override
    public int getGroupCount() {
        return categoryBeanList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //return categoryBeanList.get(groupPosition).childCategoryBean.size();
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return categoryBeanList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
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
        // first level

        View layout = convertView;
        GroupViewHolder holder;
        final CategoryBean item = (CategoryBean) getGroup(groupPosition);

        if (layout == null) {
            layout = inflater.inflate(R.layout.group_category_row, parent, false);
            holder = new GroupViewHolder();
            holder.tvTitle = (TextView) layout.findViewById(R.id.category_row_tv_title);
            layout.setTag(holder);
        } else {
            holder = (GroupViewHolder) layout.getTag();
        }

        holder.tvTitle.setText(item.title.trim());

        if (isExpanded)
            holder.tvTitle.setTextColor(mContext.getResources().getColor(android.R.color.holo_green_dark));
        else
            holder.tvTitle.setTextColor(mContext.getResources().getColor(R.color.black));

        return layout;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // second level list
        return lsfirst[groupPosition].cls;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private static class GroupViewHolder {
        TextView tvTitle;
        ImageView ivIcon;
        ImageView ivExpand;
    }

    public void doChangesAccordingly() {

    }
}
