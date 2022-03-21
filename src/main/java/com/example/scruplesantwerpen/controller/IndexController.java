package com.example.scruplesantwerpen.controller;

import com.example.scruplesantwerpen.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
class IndexController {
    private final ProductService productService;

    public IndexController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ModelAndView findAllProducts() {
        var modelAndView = new ModelAndView("index");
        return modelAndView.addObject("producten", productService.findAllProducts());
    }
}