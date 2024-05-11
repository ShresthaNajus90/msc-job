package com.reviewsMicroservice.Service;



import com.reviewsMicroservice.Entity.Review;

import java.util.List;

public interface ReviewsService {

    List<Review> getAllReviews(Long companyId);

    boolean addReview(Long companyId, Review review);
    Review getReviewById(Long reviewId);
    boolean updateReview(Long reviewsId, Review review);

    boolean deleteReview(Long reviewsId);
}
