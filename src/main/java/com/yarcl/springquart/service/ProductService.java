package com.yarcl.springquart.service;

import com.yarcl.springquart.bean.PageBean;
import com.yarcl.springquart.bean.RazorProduct;

import java.util.List;

public interface ProductService {

    public List<RazorProduct> getRazorProductByUserId(String userId, PageBean pageBean);

    public int getProductCount();

}
