package com.tmdt.controller.admin;

import com.tmdt.models.Category;
import com.tmdt.models.Product;
import com.tmdt.service.CategoryService;
import com.tmdt.service.ProductService;
import com.tmdt.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ProductService productService;

    @RequestMapping("/product")
    public String index(Model model) {
        List<Product> productList = productService.getAll();
        model.addAttribute("productList", productList);

        return "admin/product/index";
    }

//    Lay data va do vao giao dien add
    @RequestMapping("/product-add")
    public String add(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        List<Category> listCate = categoryService.getAll();
        model.addAttribute("listCate", listCate);
        return "admin/product/add";
    }

    @PostMapping("/product-add")
    public String save(@ModelAttribute("product") Product product, @RequestParam("fileImage")MultipartFile file) {
        // upload file/image
        storageService.store(file);

        String fileName = file.getOriginalFilename();
        product.setImage(fileName);
        if(productService.create(product)) {
            return "redirect:/admin/product";
        }
        return "admin/product/add";
    }
}
