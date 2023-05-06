package org.example.controller;


import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class OrderController {
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/order")
    public String showOrderPage(HttpServletRequest request, Model model) {
        model.addAttribute("order", new Order());

        model.addAttribute("username", request.getUserPrincipal().getName());
        model.addAttribute("linkOutOrUp", "/logout");
        model.addAttribute("textOutOrUp", "LogOut");
        model.addAttribute("linkInOrAccount", "/account");
        model.addAttribute("textInOrAccount", "Account");

        model.addAttribute("confirm", "false");
        model.addAttribute("another", "true");

        return "order";
    }

    @PostMapping("/order")
    public String completeOrder(@Valid Order order, BindingResult bindingResult, Model model, HttpServletRequest request) {
        model.addAttribute("username", request.getUserPrincipal().getName());
        model.addAttribute("linkOutOrUp", "/logout");
        model.addAttribute("textOutOrUp", "LogOut");
        model.addAttribute("linkInOrAccount", "/account");
        model.addAttribute("textInOrAccount", "Account");

        if (bindingResult.hasErrors()) {
            model.addAttribute("confirm", "false");
            model.addAttribute("another", "true");
            model.addAttribute(order);
            return "order";
        }

        Customer customer = customerRepository.findByUsername(request.getUserPrincipal().getName());

        order.setCustomer(customer);

        customer.orders.add(order);
        customerRepository.save(customer);

        model.addAttribute("confirm", "true");
        model.addAttribute("another", "false");
        model.addAttribute("message", "Reservation confirmed!");
        return "order";
    }
}