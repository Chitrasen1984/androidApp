package com.bravvura.gourmet.listeners;

import android.support.v7.widget.RecyclerView;

import com.bravvura.gourmet.models.ProductBean;

import java.util.ArrayList;

/**
 * Created by munchado on 26/4/17.
 */

public interface OnProductUpdateListener {

    public void updateProducts(RecyclerView recyclerView, ArrayList<ProductBean> productBeans);
}
