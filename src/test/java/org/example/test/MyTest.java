package org.example.test;

import org.example.mapper.ProductInfoMapper;
import org.example.pojo.ProductInfo;
import org.example.pojo.vo.ProductSearchVo;
import org.example.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

// These 2 annotation are used for inject the Test class to the spring application context, so that we can assign spring obejct in this test class
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext_dao.xml","classpath:applicationContext_service.xml"})
public class MyTest {

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Test
    public void testMD5(){
        String encryptedPassword = MD5Util.getMD5("000000");
        System.out.println(encryptedPassword);
    }

    @Test
    public void testConditionSearch(){
        ProductSearchVo productSearchVo = new ProductSearchVo();
        productSearchVo.setPname("4");
        productSearchVo.setTypeid(null);
        System.out.println(productSearchVo);

        List<ProductInfo> productInfos = productInfoMapper.searchProductsByConditions(productSearchVo);
        for (ProductInfo productInfo:productInfos) {
            System.out.println(productInfo);
        }
    }
}
