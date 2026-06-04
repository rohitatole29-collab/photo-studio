package com.example.studio.controller;

import com.example.studio.entity.Order;
import com.example.studio.entity.OrderItem;
import com.example.studio.entity.Photo;
import com.example.studio.repository.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DownloadController {

    @Autowired private OrderRepository orderRepo;

    @GetMapping("/download")
    public String page(HttpSession session, Model model){
        Long orderId = (Long) session.getAttribute("orderId");
        if (orderId == null) return "redirect:/gallery";
        Order order = orderRepo.findById(orderId).orElse(null);
        if (order == null || !"PAID".equals(order.getStatus())) return "redirect:/checkout";
        List<Photo> photos = order.getItems().stream().map(OrderItem::getPhoto).collect(Collectors.toList());
        model.addAttribute("photos", photos);
        return "download";
    }
}
