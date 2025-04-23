package data1700.reviews.controller;

import data1700.reviews.model.Review;
import data1700.reviews.repository.ReviewRepository;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
@Transactional
public class ReviewController {

    private final ReviewRepository repository;

    public ReviewController(ReviewRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public void createReview(Review review) {
        repository.createReview(review);
    }

    @GetMapping
    public List<Review> getAllReviews() {
        return repository.getAllReviews();
    }

    @GetMapping("/{id}")
    public Optional<Review> getReviewById(@PathVariable Long id) {
        return repository.getReviewById(id);
    }

    @GetMapping("/product/{id}")
    public List<Review> getReviewsByProductId(@PathVariable Long id) {
        return repository.getReviewsByProductId(id);
    }

    @PutMapping
    public ResponseEntity<String> updateReview(
            @PathVariable Long id,
            @RequestBody Review review) {
        int updatedRows = repository.updateReview(id,review);
        if (updatedRows > 0) {
            return ResponseEntity.ok("Updated review successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteReview(Long id) {
        repository.deleteReview(id);
    }
}
