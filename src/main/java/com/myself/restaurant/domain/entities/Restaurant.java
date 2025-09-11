package com.myself.restaurant.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.ArrayList;
import java.util.List;

// When we use @Dcument, Spring boot tells:
// Take this Java class and store its objects as JSON documents in the Elasticsearch index called restaurants
@Document(indexName = "restaurants")
@Data
@AllArgsConstructor
@Builder
public class Restaurant{

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String cuisineType;

    @Field(type = FieldType.Keyword)
    private String contactInformation;

    @Field(type = FieldType.Float) // This tells Elasticsearch that this field should be stored as a floating-point number.
    private Float averageRating;

    @GeoPointField
    private GeoPoint geoLocation; // This annotation tells Spring Data Elasticsearch that this field should
    // be stored as a geospatial point in Elasticsearch.

    // This tells Elasticsearch:
    //“This field is not a simple value like a string or number. It’s an object or list of objects, and each object
    // should be stored in a way that allows Elasticsearch to query them independently.”
    @Field(type = FieldType.Nested)
    private Address address;

    @Field(type = FieldType.Nested)
    private OperatingHours operatingHours;

    @Field(type = FieldType.Nested)
    private List<Photo> photos;

    @Field(type = FieldType.Nested)
    private List<Review> reviews;

    @Field(type = FieldType.Float)
    private User createdBy;

}
