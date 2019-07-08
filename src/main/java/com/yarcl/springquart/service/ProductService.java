package com.yarcl.springquart.service;

import com.yarcl.springquart.bean.util.PageBean;
import com.yarcl.springquart.bean.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getProductByUserId(String userId, PageBean pageBean);

    public int getProductCount();

}
