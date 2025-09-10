package com.myself.restaurant.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

// Created the timeRange entity to store opening and closing times

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeRange {

    @Field(type = FieldType.Keyword)
    private String openTime;

    @Field(type = FieldType.Keyword)
    private String closeTime;

}
