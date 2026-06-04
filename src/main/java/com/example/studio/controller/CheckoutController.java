package com.example.studio.controller;

import com.example.studio.entity.*;
import com.example.studio.repository.OrderRepository;
import com.example.studio.service.EmailService;
import com.example.studio.service.PaymentService;
import com.razorpay.RazorpayException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller @RequestMapping("/checkout")
public class CheckoutController {

    @Autowired private OrderRepository orderRepo;
    @Autowired private PaymentService paymentService;
    @Autowired private EmailService emailService;

    @GetMapping
    public String page(HttpSession session, Model model){
        List<Photo> cart = (List<Photo>) session.getAttribute("cart");
        double total = (cart == null) ? 0 : cart.stream().mapToDouble(Photo::getPrice).sum();
        model.addAttribute("total", total);
        return "checkout";
    }

    @PostMapping("/createOrder") @ResponseBody
    public String create(@RequestParam double amount) throws RazorpayException {
        com.razorpay.Order ro = paymentService.createOrder(amount);
        return ro.toString();
    }

    @PostMapping("/success")
    public String success(@RequestParam String email,
                          @RequestParam String razorpayOrderId,
                          @RequestParam String razorpayPaymentId,
                          @RequestParam String razorpaySignature,
                          HttpSession session){
        if (!paymentService.verifySignature(razorpayOrderId, razorpayPaymentId, razorpaySignature)) {
            return "redirect:/checkout?error=verify";
        }
        List<Photo> cart = (List<Photo>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) return "redirect:/gallery";
        double total = cart.stream().mapToDouble(Photo::getPrice).sum();

        Order order = new Order();
        order.setEmail(email);
        order.setTotalPrice(total);
        order.setStatus("PAID");
        cart.forEach(p -> order.addItem(new OrderItem(p)));
        orderRepo.save(order);

        session.removeAttribute("cart");
        session.setAttribute("orderId", order.getId());

        emailService.sendOrderConfirmation(email, email.split("@")[0], order.getId(), total, cart, true);
        return "redirect:/download";
    }
}
