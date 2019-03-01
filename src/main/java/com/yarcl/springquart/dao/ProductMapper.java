package com.yarcl.springquart.dao;

import com.yarcl.springquart.bean.RazorProduct;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface ProductMapper {

    public List<RazorProduct> getProductByUserId(String userId, int startPage, int endPage);


    public int getProductCount();

}
