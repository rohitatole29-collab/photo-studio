package com.example.studio.controller;

import com.example.studio.entity.Order;
import com.example.studio.repository.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class FileController {

    @Autowired private OrderRepository orderRepo;

    @GetMapping("/file/{photoId}")
    public ResponseEntity<FileSystemResource> get(@PathVariable Long photoId, HttpSession session) {
        Long orderId = (Long) session.getAttribute("orderId");
        if (orderId == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        Order order = orderRepo.findById(orderId).orElse(null);
        if (order == null || !"PAID".equals(order.getStatus())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        var itemOpt = order.getItems().stream().filter(i -> i.getPhoto().getId().equals(photoId)).findFirst();
        if (itemOpt.isEmpty()) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        String fullPath = itemOpt.get().getPhoto().getDownloadUrl();
        File f = new File(fullPath);
        if (!f.exists()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + f.getName() + "\"")
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(new FileSystemResource(f));
    }
}
