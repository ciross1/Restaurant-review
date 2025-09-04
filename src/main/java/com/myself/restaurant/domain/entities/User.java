package com.myself.restaurant.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // is a Lombok annotation that makes it easy to create objects using the builder pattern in Java,
public class User {

    // When you store Java objects in Elasticsearch using Spring Data Elasticsearch,
    // each field in your class can be
    // mapped to a type in Elasticsearch. The @Field annotation tells Elasticsearch
    // how to store and index this field.

    // Keyword fields are:
    // Not analyzed (no tokenization).
    //Stored exactly as-is.
    // If category = "Fast Food", Elasticsearch will store it exactly as "Fast Food" and won’t break it into "Fast" and "Food".
    @Field(type = FieldType.Keyword)
    private String id;

    // Tpe text: Perfect for searching within large blocks of text.
    @Field(type = FieldType.Text)
    private String username;

    @Field(type = FieldType.Text)
    private String givenName;

    @Field(type = FieldType.Text)
    private String familyName;
}
