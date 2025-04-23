package data1700.reviews;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/reviews").setViewName("forward:/reviews.html");
        registry.addViewController("/products").setViewName("forward:/products.html");
        registry.addViewController("/products/create").setViewName("forward:/createProduct.html");
        registry.addViewController("/reviews/create").setViewName("forward:/writeReview.html");
    }
}
