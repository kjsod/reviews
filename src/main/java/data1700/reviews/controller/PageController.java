package data1700.reviews.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
