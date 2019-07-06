package com.yarcl.springquart.dao;

import com.yarcl.springquart.bean.Product;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface ProductMapper {

    public List<Product> getProductByUserId(String userId, int startPage, int endPage);


    public int getProductCount();

}
