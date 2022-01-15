package org.example.service.impl;

import org.example.mapper.ProductTypeMapper;
import org.example.pojo.ProductType;
import org.example.pojo.ProductTypeExample;
import org.example.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// customize the service class name with ProductTypeServiceImpl
@Service("ProductTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> getProductTypes() {
        List<ProductType> productTypes = productTypeMapper.selectByExample(new ProductTypeExample());
        return productTypes;
    }
}
