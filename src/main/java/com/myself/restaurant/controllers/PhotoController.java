package com.myself.restaurant.controllers;

import com.myself.restaurant.domain.dtos.PhotoDto;
import com.myself.restaurant.domain.entities.Photo;
import com.myself.restaurant.mappers.PhotoMapper;
import com.myself.restaurant.services.PhotoServiceInterface;
import com.myself.restaurant.services.impl.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/photos")
public class PhotoController {

    private final PhotoService photoService;
    private final PhotoMapper photoMapper;


    @PostMapping
    public PhotoDto uploadPhoto(@RequestParam("file")MultipartFile file){

        Photo photo = photoService.uploadPhoto(file);
        return photoMapper.toDto(photo);

    }

}
