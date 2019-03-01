package com.yarcl.springquart.service.impl;

import com.yarcl.springquart.bean.PageBean;
import com.yarcl.springquart.bean.RazorProduct;
import com.yarcl.springquart.dao.ProductMapper;
import com.yarcl.springquart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductMapper productMapper;

    public int getProductCount() {
        return productMapper.getProductCount();
    }

    /*public List<RazorProduct> getProductByUserId(String userId, PageBean pageBean) {
        return productMapper.getProductByUserId(userId, CaculatePage.caculateStart(pageBean), CaculatePage.caculateEnd(pageBean));
    }*/

    @Override
    public List<RazorProduct> getRazorProductByUserId(String userId, PageBean pageBean) {
        return null;
    }
}
