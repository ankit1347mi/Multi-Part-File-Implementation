package com.tyss.image_application.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "image")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileData {
    private String id;
    private String fileName;
    private String filePath;

}
