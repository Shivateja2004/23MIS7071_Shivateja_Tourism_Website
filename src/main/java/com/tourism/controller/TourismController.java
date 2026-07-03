package com.tourism.controller;

import com.tourism.service.TourismService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TourismController {
    private final TourismService service;

    public TourismController(TourismService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("attractions", service.attractions().subList(0, 3));
        model.addAttribute("hotels", service.hotels().subList(0, 3));
        return "index";
    }

    @GetMapping("/attractions")
    public String attractions(Model model) {
        model.addAttribute("attractions", service.attractions());
        return "attractions";
    }

    @GetMapping("/hotels")
    public String hotels(Model model) {
        model.addAttribute("hotels", service.hotels());
        return "hotels";
    }

    @GetMapping("/guides")
    public String guides(Model model) {
        model.addAttribute("guides", service.guides());
        return "guides";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @PostMapping("/contact")
    public String submitContact(@RequestParam String name,
                                @RequestParam String email,
                                @RequestParam String message,
                                RedirectAttributes attributes) {
        service.saveContact(name, email, message);
        attributes.addFlashAttribute("success", "Thank you. The tourism support team will contact you soon.");
        return "redirect:/contact";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("messageCount", service.messages().size());
        model.addAttribute("messages", service.messages());
        model.addAttribute("attractionCount", service.attractions().size());
        model.addAttribute("hotelCount", service.hotels().size());
        return "admin";
    }
}
