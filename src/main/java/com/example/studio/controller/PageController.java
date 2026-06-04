package com.example.studio.controller;

import com.example.studio.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @Autowired private PhotoRepository photoRepo;

    @GetMapping({"/", "/gallery"})
    public String gallery(Model model){
        model.addAttribute("photos", photoRepo.findAllByPublishedTrueOrderByIdDesc());
        return "gallery";
    }

    @GetMapping("/about") public String about(){ return "about"; }
    @GetMapping("/services") public String services(){ return "services"; }
    @GetMapping("/portfolio") public String portfolio(){ return "portfolio"; }
    @GetMapping("/contact") public String contact(){ return "contact"; }

    @GetMapping("/admin/login") public String login(){ return "admin/login"; }
    @GetMapping("/admin") public String admin(){ return "admin/dashboard"; }
}
