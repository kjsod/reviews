package data1700.reviews.controller;

import data1700.reviews.model.Product;
import data1700.reviews.repository.ProductRepository;
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
    public void createProduct(Product product) {
        repository.createProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return repository.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return repository.getProductById(id);
    }

    @PutMapping
    public ResponseEntity<String> updateProduct(@PathVariable Long id,
                                                @RequestBody Product product) {
        int updatedRows = repository.updateProduct(id, product);
        if (updatedRows > 0) {
            return ResponseEntity.ok("Product updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(Long id) {
        repository.deleteProduct(id);
    }
}
