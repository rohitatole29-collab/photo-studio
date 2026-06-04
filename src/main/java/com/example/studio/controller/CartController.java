package com.example.studio.controller;

import com.example.studio.entity.Photo;
import com.example.studio.repository.PhotoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private PhotoRepository photoRepo;

    @PostMapping("/add/{id}")
    public String add(@PathVariable Long id, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<Photo> cart = (List<Photo>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        Optional<Photo> optionalPhoto = photoRepo.findById(id);
        if (optionalPhoto.isPresent()) {
            Photo p = optionalPhoto.get();
            boolean exists = cart.stream().anyMatch(x -> Objects.equals(x.getId(), p.getId()));
            if (!exists) {
                cart.add(p);
            }
        }

        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @GetMapping
    public String view(HttpSession session, Model model) {
        @SuppressWarnings("unchecked")
        List<Photo> cart = (List<Photo>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        double total = cart.stream()
                .filter(Objects::nonNull)
                .mapToDouble(Photo::getPrice)
                .sum();

        model.addAttribute("cartItems", cart);
        model.addAttribute("total", total);
        return "cart";
    }

    @PostMapping("/remove/{id}")
    public String remove(@PathVariable Long id, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<Photo> cart = (List<Photo>) session.getAttribute("cart");

        if (cart != null) {
            cart.removeIf(p -> p != null && Objects.equals(p.getId(), id));
        }

        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }
}
