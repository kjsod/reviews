package data1700.reviews.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Review {

    private Long reviewid;
    private String review;
    private int stars;
    private int productid;

    public Review(Long reviewid, String review, int stars, int productid) {
        this.reviewid = reviewid;
        this.review = review;
        this.stars = stars;
        this.productid = productid;
    }
}
