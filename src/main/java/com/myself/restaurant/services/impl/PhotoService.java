package com.myself.restaurant.services.impl;

import com.myself.restaurant.domain.entities.Photo;
import com.myself.restaurant.services.PhotoServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhotoService implements PhotoServiceInterface {

    private final StorageService storageService;




    @Override
    public Photo uploadPhoto(MultipartFile file) {
      String photoId =   UUID.randomUUID().toString();
        String url  = storageService.store(file,photoId );

        return Photo.builder()
                .url(url)
                .uploadDate(LocalDateTime.now())
                .build();



    }

    @Override
    public Optional<Resource> getPhotoResources(String id) throws MalformedURLException {
       return storageService.loadAsResource(id);
    }
}
