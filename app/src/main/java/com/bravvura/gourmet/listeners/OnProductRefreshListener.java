package com.bravvura.gourmet.listeners;

import android.support.v7.widget.RecyclerView;

import com.bravvura.gourmet.models.ProductBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by munchado on 26/4/17.
 */

public interface OnProductRefreshListener {

    public void onRefreshProducts();

    public void onClickProduct();
}
