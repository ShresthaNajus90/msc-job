package com.reviewsMicroservice.reviewImpl;


import com.reviewsMicroservice.Entity.Review;
import com.reviewsMicroservice.Repository.ReviewRepository;
import com.reviewsMicroservice.Service.ReviewsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewsService {

    private final ReviewRepository reviewRepository;


    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;

    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review ) {

        if(companyId != null && review != null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Review getReviewById(Long reviewId) {
               return reviewRepository.findById(reviewId).orElse(null) ;

    }

    @Override
    public boolean updateReview(Long reviewsId, Review updatedReview) {
        Review review = reviewRepository.findById(reviewsId).orElse(null);
        if(reviewsId != null){
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setCompanyId(updatedReview.getCompanyId());
            review.setId(reviewsId);
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long reviewsId) {
        Review review = reviewRepository.findById(reviewsId).orElse(null);
        if(review != null) {
            reviewRepository.delete(review);
            return true;
        } else {
            return false;
        }
    }


}
