package com.tmdt.controller.admin;

import com.tmdt.models.Category;
import com.tmdt.service.CategoryService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public String index(Model model, @Param("keyword")  String keyword, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Category> list = categoryService.getAll(pageNo);
        if(keyword != null) {
            list = categoryService.searchCategory(keyword, pageNo);
            model.addAttribute("keyword", keyword);
        }
        //total page and current page
        model.addAttribute("totalPage", list.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("list", list);
        return "admin/category/index";
    }

    @GetMapping("/add-category")
    public String add(Model model) {
        Category category = new Category();
        category.setCategoryStatus(true);
        model.addAttribute("category", category);
        return "admin/category/add";
    }
    @PostMapping("/add-category")
    public String save(@ModelAttribute("category") Category category) { //lay category tu giao dien
        if(categoryService.create(category)) {
            return "redirect:/admin/category";
        } else {
            return "admin/category/add";
        }
    }

//    Lay data de day vao form edit
    @GetMapping("/edit-category/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) { //lay id tu path
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "admin/category/edit";
    }

//    Lay data tu form va cap nhat
    @PostMapping("/edit-category")
    public String update(@ModelAttribute("category") Category category) {
        if(categoryService.update(category)) {
            return "redirect:/admin/category";
        } else {
            return "admin/category/edit";
        }
    }

    @GetMapping("/delete-category/{id}")
    public String delete(@PathVariable("id") Integer id) {
        if(categoryService.delete(id)) {
            return "redirect:/admin/category";
        } else {
            return "redirect:/admin/category";
        }
    }
}
