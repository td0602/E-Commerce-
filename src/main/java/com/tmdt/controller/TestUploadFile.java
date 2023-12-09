package com.tmdt.controller;

import com.tmdt.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload-test")
public class TestUploadFile {
    @Autowired
    private StorageService storageService;

    @RequestMapping
    public String uploadDemo() {
        return "test-upload";
    }
    @PostMapping
    public String save(@RequestParam("file")MultipartFile file) {
        storageService.store(file);
        return "test-upload";
    }
}
