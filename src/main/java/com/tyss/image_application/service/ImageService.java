package com.tyss.image_application.service;

import com.tyss.image_application.entity.FileData;
import com.tyss.image_application.entity.Image;
import com.tyss.image_application.repo.FileDataRepo;
import com.tyss.image_application.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepo imageRepository;

    @Autowired
    private FileDataRepo fileDataRepo;

    private final String FOLDER_PATH = "C:\\files";

    public Image saveImage(String name, MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(name);
        image.setData(file.getBytes());
        return imageRepository.save(image);
    }

    public Image getImage(String id) {
        return imageRepository.findById(id).orElse(null);
    }

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH + file.getOriginalFilename();
        FileData fileData = fileDataRepo.save(FileData.builder()
                .fileName(file.getOriginalFilename())
                .filePath(filePath)
                .build());
        file.transferTo(new File(filePath));
        if (fileData != null) {
            return "file upload successfully:" + filePath;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String name) throws IOException {
        Optional<FileData> fileData = fileDataRepo.findByFileName(name);
        String filePath = fileData.get().getFilePath();
        byte[] image = Files.readAllBytes(new File(filePath).toPath());
        return image;
    }
}
