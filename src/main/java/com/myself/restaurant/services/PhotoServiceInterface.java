package com.myself.restaurant.services;

import com.myself.restaurant.domain.entities.Photo;
i
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.util.Optional;

public interface PhotoServiceInterface  {

    Photo uploadPhoto(MultipartFile file);
    Optional<Resource> getPhotoResources(String id) throws MalformedURLException;
}
