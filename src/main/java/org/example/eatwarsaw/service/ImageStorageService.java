package org.example.eatwarsaw.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImageStorageService {

    private final Path uploadDir = Paths.get("uploads");

    public ImageStorageService() throws IOException {
        Files.createDirectories(uploadDir);
    }

    public String saveImage(MultipartFile file, String subfolder) {
        try {
            String extension = getExtension(Objects.requireNonNull(file.getOriginalFilename()));
            String filename = UUID.randomUUID() + "." + extension;
            Path targetPath = uploadDir.resolve(subfolder).resolve(filename);
            Files.createDirectories(targetPath.getParent());
            file.transferTo(targetPath);
            return "/uploads/" + subfolder + "/" + filename;
        } catch (IOException e) {
            throw new RuntimeException("Не вдалося зберегти файл", e);
        }
    }

    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf('.') + 1);
    }
}
