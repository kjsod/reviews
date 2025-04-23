package data1700.reviews.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/products")
    public String products() {
        return "products.html";
    }

}
