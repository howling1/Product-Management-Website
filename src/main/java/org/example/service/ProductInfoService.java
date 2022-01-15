package org.example.service;

import com.github.pagehelper.PageInfo;
import org.example.pojo.ProductInfo;
import org.example.pojo.vo.ProductSearchVo;

import java.util.List;

public interface ProductInfoService {

    // display all commodities without pagination
    List<ProductInfo> getAllProducts();

    // display commodities with pagination via pageHelper plugin
    PageInfo getPaginatedProducts(Integer pageNum, Integer pageSize);

    // add new commodity
    Integer save(ProductInfo productInfo);

    ProductInfo getProductById(Integer id);

    Integer updateProductInfo(ProductInfo productInfo);

    Integer deleteProduct(Integer id);

    Integer deleteBatchProducts(String []ids);

    List<ProductInfo> selectProductsByCondition(ProductSearchVo productSearchVo);

    PageInfo<ProductInfo> paginateProductsByConditionSearch(ProductSearchVo productSearchVo,Integer pageSize);


}
