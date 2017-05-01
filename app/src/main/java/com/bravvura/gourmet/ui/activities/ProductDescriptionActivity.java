package com.bravvura.gourmet.ui.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bravvura.gourmet.BuildConfig;
import com.bravvura.gourmet.R;
import com.bravvura.gourmet.models.ProductBean;
import com.bravvura.gourmet.utils.Constants;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * Created by munchado on 28/4/17.
 */

public class ProductDescriptionActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = BuildConfig.BASE_TAG + "." + "ProductDescriptionActivity";

    @Bind(R.id.toolbar)
    Toolbar toolbar;

  /*  @Bind(R.id.toolbar_item_container)
    LinearLayout toolbarLinearLayout;*/

    @Bind(R.id.activity_product_description_pb)
    ProgressBar progressBar;

    @Bind(R.id.activity_product_description_scroll_view)
    ScrollView scrollView;

    @Bind(R.id.activity_product_description_tv_product_title)
    TextView tvProductTitle;

    @Bind(R.id.activity_product_description_tv_product_description)
    TextView tvProductDes;

    @Bind(R.id.activity_product_description_tv_product_quantity)
    TextView tvQuantity;

    @Bind(R.id.activity_product_description_tv_product_price)
    TextView tvPrice;

    @Bind(R.id.activity_product_description_rv_similar_products)
    RecyclerView recyclerView_SimProducts;

    @Bind(R.id.activity_product_description_rv_combi_products)
    RecyclerView recyclerView_CombiProducts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);
        ButterKnife.bind(this);

        initToolbar();

        //initiateToolbarView();

        initData();
    }

    private void initToolbar() {
        //toolbar.setLogo(R.mipmap.gourmet_logo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*private void initiateToolbarView() {
        View toolbarView = LayoutInflater.from(this).inflate(R.layout.toolbar_view_product_description_screen, null);
        *//*RelativeLayout relativeLayout = (RelativeLayout) toolbarView.findViewById(R.id.toolbar_view_product_des_screen_rl_container);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(toolbar.getWidth(), toolbar.getHeight()));*//*
        ImageView ivBack = (ImageView) toolbarView.findViewById(R.id.toolbar_view_product_des_screen_iv_back);
        ivBack.setOnClickListener(this);

        toolbarLinearLayout.addView(toolbarView);
    }*/

  /*@OnClick(R.id.toolbar_view_product_des_screen_iv_back)
  private void onBack(){

  }*/

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.toolbar_view_product_des_screen_iv_back) {
            onBackPressed();
        }
    }

    private void initData() {
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);

                ProductBean productBean = getProductDetail();

                tvProductTitle.setText(productBean.productTitle);
                tvProductDes.setText(productBean.productDes);
                tvQuantity.setText(productBean.quantity);
                tvPrice.setText(productBean.currency + productBean.productPrice);

            }
        }, 1000);
    }

    private ProductBean getProductDetail() {
        ProductBean productBean = new ProductBean();
        productBean.productTitle = "Chilled Aussie Beef Shabu Sirloin";
        productBean.productDes = "Very thin slices of chilled Aussie grained beef sirloin with its fat cover perfect for Japenese Shabu Shabu Hot Pot. We make this by special order only so please allow us 6 hours advance notice and minimum order will be 1 kg. so please allow us 6 hours advance notice and minimum order will be 1 kg.";
        productBean.productPrice = 520.00;
        productBean.quantity = "1kg";
        productBean.currency = "EGP";
        return productBean;
    }

    private void getSimilarProductList() {

        ArrayList<ProductBean> productBeanList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ProductBean productBean= new ProductBean();

        }
    }
}
