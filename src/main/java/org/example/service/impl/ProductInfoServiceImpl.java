package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.mapper.ProductInfoMapper;
import org.example.pojo.ProductInfo;
import org.example.pojo.ProductInfoExample;
import org.example.pojo.vo.ProductSearchVo;
import org.example.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> getAllProducts() {
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }

    @Override
    public PageInfo getPaginatedProducts(Integer pageNum, Integer pageSize) {
        // this config should be done before querying database
        PageHelper.startPage(pageNum, pageSize);
        //select * from product_info order by p_id desc
        ProductInfoExample productInfoExample = new ProductInfoExample();
        productInfoExample.setOrderByClause("p_id desc");
        List<ProductInfo> productInfos = productInfoMapper.selectByExample(productInfoExample);

        // pagination via pageHelper, return a PageInfo object containing all information about the required page(items, page size, page number, etc.)
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(productInfos);

        return pageInfo;
    }

    @Override
    public Integer save(ProductInfo productInfo) {

        return productInfoMapper.insert(productInfo);
    }

    @Override
    public ProductInfo getProductById(Integer id) {
        return productInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateProductInfo(ProductInfo productInfo) {
        return productInfoMapper.updateByPrimaryKey(productInfo);
    }

    @Override
    public Integer deleteProduct(Integer id) {
        return productInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteBatchProducts(String[] ids) {
        return productInfoMapper.deleteBatchProducts(ids);
    }

    @Override
    public List<ProductInfo> selectProductsByCondition(ProductSearchVo productSearchVo) {
        return productInfoMapper.searchProductsByConditions(productSearchVo);
    }

    @Override
    public PageInfo<ProductInfo> paginateProductsByConditionSearch(ProductSearchVo productSearchVo, Integer pageSize) {
        PageHelper.startPage(productSearchVo.getPageNum(),pageSize);
        List<ProductInfo> productInfos = productInfoMapper.searchProductsByConditions(productSearchVo);
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(productInfos);
        return pageInfo;
    }
}
