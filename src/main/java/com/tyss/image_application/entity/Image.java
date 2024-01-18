package com.tyss.image_application.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "image")
@Data
public class Image {

    private String id;

    private String name;

    private byte[] data;
}
