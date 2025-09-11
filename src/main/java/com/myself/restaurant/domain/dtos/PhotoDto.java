package com.myself.restaurant.domain.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PhotoDto {

    private String url; 
    private LocalDateTime uploadDate;


}
