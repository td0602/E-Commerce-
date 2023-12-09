package com.tmdt.service;

import com.tmdt.models.Product;
import com.tmdt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Boolean create(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Product findById(Integer id) {
        return null;
    }

    @Override
    public Boolean update(Product product) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }
}
