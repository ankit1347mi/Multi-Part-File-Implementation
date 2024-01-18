package com.tyss.image_application.repo;

import com.tyss.image_application.entity.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepo extends MongoRepository<Image,String> {
}
