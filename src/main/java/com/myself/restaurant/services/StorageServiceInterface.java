package com.myself.restaurant.services;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.util.Optional;

public interface StorageServiceInterface {
    String store(MultipartFile file, String fileName);  //Method to store multipart file
    Optional<Resource> loadAsResource(String id) throws MalformedURLException; // Method to load that file
}
