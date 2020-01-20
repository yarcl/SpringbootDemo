package com.yarcl.springquart.dao;

import com.yarcl.springquart.bean.product.Product;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface ProductMapper {

    List<Product> getProductByUserId(String userId, int startPage, int endPage);


    int getProductCount();

}
