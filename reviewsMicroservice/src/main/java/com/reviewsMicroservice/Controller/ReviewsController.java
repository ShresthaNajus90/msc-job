package com.reviewsMicroservice.Controller;


import com.reviewsMicroservice.Entity.Review;
import com.reviewsMicroservice.Service.ReviewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    private ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    // getting Companies all reviews
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
        return new ResponseEntity<>(reviewsService.getAllReviews(companyId), HttpStatus.OK);
    }

    // adding company reviews

    @PostMapping
    public ResponseEntity<String> addReviews(@RequestParam Long companyId, @RequestBody Review review) {
        boolean isReviewSaved = reviewsService.addReview(companyId, review);
        if (isReviewSaved)
            return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Review not Saved", HttpStatus.NOT_FOUND);

    }

    //fetching reviews by reviewsId
    @GetMapping("/{reviewsId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewsId) {
        return new ResponseEntity<>(reviewsService.getReviewById(reviewsId),
                HttpStatus.OK);

    }

    @PutMapping("/{reviewsId}")
    public ResponseEntity<String> updateReviews(@PathVariable Long reviewsId,@RequestBody Review review) {
        boolean isReviewUpdated = reviewsService.updateReview(reviewsId, review);
        if (isReviewUpdated)
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
         else
           return new ResponseEntity<>("Review not updated !!", HttpStatus.NOT_FOUND);
        }


     @DeleteMapping("/{reviewsId}")
    public ResponseEntity<String> deleteReviewById( @PathVariable Long reviewsId){
        boolean isReviewDeleted = reviewsService.deleteReview(reviewsId);
        if (isReviewDeleted) {
            return new ResponseEntity<>("Review deleted Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review not deleted", HttpStatus.BAD_REQUEST);


         }
     }

}




