package com.tmdt.service;

import com.tmdt.models.Category;
import com.tmdt.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Boolean create(Category category) {
        try {
            categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Boolean update(Category category) {
        try {
            categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            categoryRepository.delete(findById(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<Category> searchCategory(String keyword) {
        return categoryRepository.searchCategory(keyword);
    }

    @Override
    public Page<Category> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1, 2); //(trang bắt đầu, giới hạn số product moi trang)
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Page<Category> searchCategory(String keyword, Integer pageNo) {
        List list = searchCategory(keyword);
        Pageable pageable = PageRequest.of(pageNo-1, 2);
        // list phu
        Integer start = Math.toIntExact(pageable.getOffset());
        Integer end = Math.toIntExact((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size() : pageable.getOffset() + pageable.getPageSize());
        list = list.subList(start, end);
        return new PageImpl<Category>(list, pageable, searchCategory(keyword).size());
    }
}
