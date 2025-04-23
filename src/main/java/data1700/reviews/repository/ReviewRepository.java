package data1700.reviews.repository;

import data1700.reviews.model.Review;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReviewRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReviewRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Review> rowMapper = (rs, rowNum) ->
            new Review(
                    rs.getLong("reviewid"),
                    rs.getString("review"),
                    rs.getInt("stars"),
                    rs.getInt("productid")
            );

    public Long createReview(Review review) {
        String sql = "INSERT INTO review(review,stars,productid) VALUES(?,?,?)";
        jdbcTemplate.update(
                sql,
                review.getReview(),
                review.getStars(),
                review.getProductid());
        return review.getReviewid();
    }

    public List<Review> getAllReviews() {
        String sql = "SELECT * FROM review";
        return jdbcTemplate.query(sql,rowMapper);
    }

    public Optional<Review> getReviewById(Long id) {
        String sql = "SELECT * FROM review WHERE productid = ?";
        return jdbcTemplate.query(sql,rowMapper,id).stream().findFirst();
    }

    public List<Review> getReviewsByProductId(Long id) {
        String sql = "SELECT * FROM review WHERE productid = ?";
        return jdbcTemplate.query(sql,rowMapper,id);
    }

    public int updateReview(Long id, Review review) {
        String sql = "UPDATE review SET review = ?, stars = ?, productid = ? WHERE id = ?";
        return jdbcTemplate.update(
                sql,
                review.getReview(),
                review.getStars(),
                review.getReviewid(),
                id);
    }

    public void deleteReview(Long id) {
        String sql = "DELETE FROM review WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }
}
