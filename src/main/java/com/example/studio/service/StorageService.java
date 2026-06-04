package com.example.studio.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;
import java.io.IOException;

@Service
public class StorageService {
    private final Path root = Paths.get("uploads").toAbsolutePath();

    public String storePreview(MultipartFile file){ return store(file, "previews"); }
    public String storeFull(MultipartFile file){ return store(file, "full"); }

    private String store(MultipartFile file, String folder){
        try{
            Path dir = root.resolve(folder);
            Files.createDirectories(dir);
            String original = file.getOriginalFilename()==null ? "file" : file.getOriginalFilename();
            String base = original.replaceAll("[^a-zA-Z0-9._-]", "_");
            String fname = System.currentTimeMillis() + "-" + base;
            Path dest = dir.resolve(fname).normalize();
            Files.copy(file.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);
            if("previews".equals(folder)) return "/uploads/previews/" + fname;
            return dest.toString(); // full path (private)
        }catch(IOException e){ throw new RuntimeException("File store failed", e); }
    }
}
