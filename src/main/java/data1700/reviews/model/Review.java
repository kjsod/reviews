package data1700.reviews.model;

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

    public Long getReviewid() {
        return reviewid;
    }

    public void setReviewid(Long reviewid) {
        this.reviewid = reviewid;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }
}
