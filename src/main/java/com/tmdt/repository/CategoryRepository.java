package com.tmdt.repository;

import com.tmdt.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    // %?1% la 1 tham so
    @Query("SELECT c FROM Category c WHERE c.categoryName LIKE %?1%")
    List<Category> searchCategory(String keyword);

}
