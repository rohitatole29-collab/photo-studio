package com.example.studio.controller.admin;

import com.example.studio.entity.Photo;
import com.example.studio.repository.PhotoRepository;
import com.example.studio.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller @RequestMapping("/admin")
public class AdminController {

    @Autowired private PhotoRepository photos;
    @Autowired private StorageService storage;

    @GetMapping("/photos")
    public String list(Model m){
        m.addAttribute("photos", photos.findAll());
        return "admin/photos";
    }

    @GetMapping("/photos/new")
    public String form(Model m){
        m.addAttribute("photo", new Photo());
        return "admin/photo-form";
    }

    @PostMapping("/photos")
    public String create(@RequestParam String name, @RequestParam double price,
                         @RequestParam("preview") MultipartFile preview,
                         @RequestParam("full") MultipartFile full){
        String previewUrl = storage.storePreview(preview);
        String fullPath = storage.storeFull(full);
        Photo p = new Photo();
        p.setName(name);
        p.setSlug(name.toLowerCase().replaceAll("[^a-z0-9]+","-"));
        p.setPrice(price);
        p.setPreviewUrl(previewUrl);
        p.setDownloadUrl(fullPath);
        p.setPublished(true);
        photos.save(p);
        return "redirect:/admin/photos";
    }

    @PostMapping("/photos/{id}/publish")
    public String toggle(@PathVariable Long id, @RequestParam boolean published){
        photos.findById(id).ifPresent(p -> { p.setPublished(published); photos.save(p); });
        return "redirect:/admin/photos";
    }
}
