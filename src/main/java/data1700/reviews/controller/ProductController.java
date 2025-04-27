package data1700.reviews.controller;

import data1700.reviews.model.Product;
import data1700.reviews.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@Transactional
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        if (repository.existsByDescription(product.getDescription())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Product already exists");
        }

        long saved = repository.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return repository.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return repository.getProductById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id,
                                                @RequestBody Product product) {
        int updatedRows = repository.updateProduct(id, product);
        if (updatedRows > 0) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Product updated successfully");
        } else {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Product already exists");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        repository.deleteProduct(id);
    }
}
