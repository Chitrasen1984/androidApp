package com.bravvura.gourmet.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bravvura.gourmet.R;
import com.bravvura.gourmet.listeners.OnProductRefreshListener;
import com.bravvura.gourmet.models.CategoryBean;
import com.bravvura.gourmet.models.ProductBean;
import com.bravvura.gourmet.models.ProductCategory;
import com.bravvura.gourmet.ui.activities.ProductDescriptionActivity;
import com.bravvura.gourmet.ui.adapters.CategoryListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * Created by munchado on 26/4/17.
 */

public class CategoryFragment extends Fragment implements OnProductRefreshListener {

    @Bind(R.id.fragment_category_rv_categories)
    RecyclerView recyclerViewCategory;

    @Bind(R.id.fragment_category_rv_product)
    RecyclerView recyclerViewProduct;

    @Bind(R.id.fragment_category_progress_bar)
    ProgressBar progressBar;

    private SectionedRecyclerViewAdapter sectionAdapter;

    private int selectedCategoryPosition = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*if (getActivity() != null && getActivity() instanceof OnToolbarViewChangeListener) {
            ((OnToolbarViewChangeListener) getActivity()).onChangeToolbarView(Constants.TAG_CATEGORY_SCREEN);
        }*/
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewCategory.setLayoutManager(linearLayoutManager);
        recyclerViewCategory.setAdapter(new CategoryListAdapter(getActivity(), prepareListData(), CategoryFragment.this, selectedCategoryPosition));

        initProductContainer();
    }

    private void initProductContainer() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerViewProduct.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                recyclerViewProduct.setVisibility(View.VISIBLE);

                sectionAdapter = new SectionedRecyclerViewAdapter();
                getProductList();

                GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
                glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        switch (sectionAdapter.getSectionItemViewType(position)) {
                            case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
                                return 2;
                            default:
                                return 1;
                        }
                    }
                });
                recyclerViewProduct.setLayoutManager(glm);
                recyclerViewProduct.setAdapter(sectionAdapter);

                /*LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewProduct.setLayoutManager(linearLayoutManager);
                getProductList();
                recyclerViewProduct.setAdapter(new ProductListAdapter(getActivity(), prepareProductData(), CategoryFragment.this));*/

            }
        }, 1000);
    }

    private void getProductList() {
        for (int i = 0; i < 10; i++) {

            List<ProductBean> productBeanList = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                ProductBean productBean = new ProductBean();
                if (j == 0) {
                    productBean.productTitle = "South African Angus Veal Rump Cap Roast";
                    productBean.productPrice = 184.00;
                    productBean.quantity = "800g";
                } else if (j == 1) {
                    productBean.productTitle = "Danish Beef Egyptian Sausage";
                    productBean.productPrice = 39.60;
                    productBean.quantity = "400g";
                } else if (j == 2) {
                    productBean.productTitle = "Fresh Chicken Tandoori Shish Tawook";
                    productBean.productPrice = 63.75;
                    productBean.quantity = "600g";
                } else if (j == 3) {
                    productBean.productTitle = "Danish Beef Topside Steak(s)";
                    productBean.productPrice = 67.50;
                    productBean.quantity = "700g";
                }
                productBean.currency = "EGP";
                productBeanList.add(productBean);
            }
            ProductCategory productCategory = null;
            if (i == 0)
                productCategory = new ProductCategory("Most Popular", productBeanList, getActivity(), CategoryFragment.this);
            else if (i == 1)
                productCategory = new ProductCategory("All Time Favorites", productBeanList, getActivity(), CategoryFragment.this);
            else if (i == 2)
                productCategory = new ProductCategory("Weekly Supplies", productBeanList, getActivity(), CategoryFragment.this);
            else if (i == 3)
                productCategory = new ProductCategory("Most popular-1", productBeanList, getActivity(), CategoryFragment.this);
            else if (i == 4)
                productCategory = new ProductCategory("All Time Favorites-1", productBeanList, getActivity(), CategoryFragment.this);
            else if (i == 5)
                productCategory = new ProductCategory("Weekly Supplies-1", productBeanList, getActivity(), CategoryFragment.this);
            else if (i == 6)
                productCategory = new ProductCategory("Most Popular-2", productBeanList, getActivity(), CategoryFragment.this);
            else if (i == 7)
                productCategory = new ProductCategory("All Time Favorites-2", productBeanList, getActivity(), CategoryFragment.this);
            else if (i == 8)
                productCategory = new ProductCategory("Weekly Supplies-2", productBeanList, getActivity(), CategoryFragment.this);
            else if (i == 9)
                productCategory = new ProductCategory("Weekly Supplies-3", productBeanList, getActivity(), CategoryFragment.this);
            sectionAdapter.addSection(productCategory);
        }
    }

    private ArrayList<CategoryBean> prepareListData() {

        ArrayList<CategoryBean> categoryBeanArrayList = new ArrayList<>();

        for (int k = 0; k < 6; k++) {
            CategoryBean categoryBean = new CategoryBean();
            if (k == 0)
                categoryBean.title = "Vegan/Vegetarian";
            else if (k == 1)
                categoryBean.title = "Appetizers";
            else if (k == 2)
                categoryBean.title = "Sides";
            else if (k == 3)
                categoryBean.title = "Mains";
            else if (k == 4)
                categoryBean.title = "Lamb";
            else if (k == 5)
                categoryBean.title = "Poultry";
            categoryBeanArrayList.add(categoryBean);
        }

        return categoryBeanArrayList;
    }

    /*private ArrayList<ProductCategory> prepareProductData() {
        ArrayList<ProductCategory> productCategories = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ProductCategory productCategory = new ProductCategory();
            if (i == 0)
                productCategory.header = "Most Popular";
            else if (i == 1)
                productCategory.header = "All Time Favorites";
            else if (i == 2)
                productCategory.header = "Weekly Supplies";
            else if (i == 3)
                productCategory.header = "Most popular-1";
            else if (i == 4)
                productCategory.header = "All Time Favorites-1";
            else if (i == 5)
                productCategory.header = "Weekly Supplies-1";
            else if (i == 6)
                productCategory.header = "Most Popular-2";
            else if (i == 7)
                productCategory.header = "All Time Favorites-2";
            else if (i == 8)
                productCategory.header = "Weekly Supplies-2";
            else if (i == 9)
                productCategory.header = "Weekly Supplies-3";

            for (int j = 0; j < 5; j++) {
                ProductBean productBean = new ProductBean();
                if (j == 0) {
                    productBean.productTitle = "South African Angus Veal Rump Cap Roast";
                    productBean.productPrice = 184.00;
                    productBean.quantity = "800g";
                } else if (j == 1) {
                    productBean.productTitle = "Danish Beef Egyptian Sausage";
                    productBean.productPrice = 39.60;
                    productBean.quantity = "400g";
                } else if (j == 2 && j == 4) {
                    productBean.productTitle = "Fresh Chicken Tandoori Shish Tawook";
                    productBean.productPrice = 63.75;
                    productBean.quantity = "600g";
                } else if (j == 3) {
                    productBean.productTitle = "Danish Beef Topside Steak(s)";
                    productBean.productPrice = 67.50;
                    productBean.quantity = "700g";
                }
                productBean.currency = "EPG";

                productCategory.productBean.add(productBean);
            }
            productCategories.add(productCategory);
        }
        return productCategories;
    }*/

    @Override
    public void onRefreshProducts() {
       /* GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rViewProduct.setLayoutManager(gridLayoutManager);
        rViewProduct.setAdapter(new ProductViewAdapter(getActivity(), productBeans));*/

        initProductContainer();
    }

    @Override
    public void onClickProduct() {
        Intent intent = new Intent(getActivity(), ProductDescriptionActivity.class);
        startActivity(intent);
    }
}
