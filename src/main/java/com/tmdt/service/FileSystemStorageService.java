package com.tmdt.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageService implements StorageService{
//  duong dan luu image
    private final Path rootLocation;

    public FileSystemStorageService() {
        rootLocation = Paths.get("src/main/resources/static/uploads");
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void store(MultipartFile file) {
        try {
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING); //Neu ton tai se thay the
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
