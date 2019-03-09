package com.yarcl.springquart.controller;

import com.yarcl.springquart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping("/myProductInfo.do")
    public ModelAndView allProductInfo(ModelAndView mav, String userId, String nowPage){
        System.out.println("hello product!!!");
        /*if(nowPage!=null && !"".equals(nowPage)){
            pageBean.setNowPage(Integer.parseInt(nowPage));
        }
        List<RazorProduct> razorProductList = razorProductService.getRazorProductByUserId(userId, pageBean);
        pageBean.setCount(razorProductService.getProductCount());
        mav.addObject("razorProductList", razorProductList);
        mav.addObject("page", pageBean);
        */
        mav.setViewName("/page/product/productList.html");
        return mav;
    }

}
