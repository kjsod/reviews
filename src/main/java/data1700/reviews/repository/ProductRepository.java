package data1700.reviews.repository;

import data1700.reviews.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Product> rowMapper = (rs, rowNum) ->
            new Product(
                    rs.getLong("productid"),
                    rs.getString("description")
            );

    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql,rowMapper);
    }

    public Optional<Product> getProductById(Long id) {
        String sql = "SELECT * FROM product WHERE productid = ?";
        return jdbcTemplate.query(sql,rowMapper,id).stream().findFirst();
    }

    public void createProduct(Product product) {
        String sql = "INSERT INTO product(description) VALUES(?)";
        jdbcTemplate.update(
                sql,
                product.getDescription());
    }

    public int updateProduct(Long id, Product product) {
        String sql = "UPDATE product SET description = ? WHERE id = ?";
        return jdbcTemplate.update(
                sql,
                product.getDescription(),
                id);
    }

    public void deleteProduct(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }
}
