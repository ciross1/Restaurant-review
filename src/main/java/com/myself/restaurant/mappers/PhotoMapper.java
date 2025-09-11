package com.myself.restaurant.mappers;

import com.myself.restaurant.domain.dtos.PhotoDto;
import com.myself.restaurant.domain.entities.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;



@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhotoMapper {


    // Declares a method that converts a Photo entity into a PhotoDto.
//You don’t implement this method manually—MapStruct generates the code automatically.
//For example, if Photo has id, title, url fields, the generated method will copy these into a new PhotoDto.
    //
    // basically it copies fields from Photo to PhotoDto
    PhotoDto toDto(Photo photo);
}




// componentModel = "spring"
//Tells MapStruct to generate the mapper as a Spring bean, so you can inject it anywhere in your Spring application:
