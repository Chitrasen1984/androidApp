package com.bravvura.gourmet.models;

import java.util.List;

/**
 * Created by munchado on 21/4/17.
 */

public class CategoryBean {

    public int id;
    public String title;
    public List<CategoryBean> childCategoryBean;
}
