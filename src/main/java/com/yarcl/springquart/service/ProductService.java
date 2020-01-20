package com.yarcl.springquart.service;

import com.yarcl.springquart.bean.PageBean;
import com.yarcl.springquart.bean.product.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getRazorProductByUserId(String userId, PageBean pageBean);

    public int getProductCount();

}
