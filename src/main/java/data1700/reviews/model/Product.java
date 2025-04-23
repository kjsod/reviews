package data1700.reviews.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private Long id;
    private String description;

    public Product(Long id, String description) {
        this.id = id;
        this.description = description;
    }
}
