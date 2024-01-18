package com.tyss.image_application.repo;

import com.tyss.image_application.entity.FileData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.File;
import java.util.Optional;

public interface FileDataRepo extends MongoRepository<FileData,String> {
    Optional<FileData> findByFileName(String name);
}
