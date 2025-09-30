package com.embarkx.firstjobapp.Review;


import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @RestController is a REST-ful web service controller. that combines the functionality of both @Controller and @ResponseBody. --->@Controller → Handles HTTP requests and returns a view name (like an HTML page).---> @ResponseBody → Tells Spring to take the method’s return value and directly write it as JSON or XML in the HTTP response.
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId ,@RequestBody Review review){
        boolean idReviewSaved = reviewService.addReview(companyId, review);
        if(idReviewSaved){
            return new ResponseEntity<>("Review added Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review not added", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReview(companyId, reviewId), HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review) {
        boolean isReviewUpdated = reviewService.updateReview(companyId, reviewId, review);
        if (isReviewUpdated) {
            return new ResponseEntity<>("Review updated Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review Not Updated", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        boolean isReviewDeleted = reviewService.deleteReview(companyId, reviewId);
        if (isReviewDeleted) {
            return new ResponseEntity<>("Review Deleted Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review Not Deleted", HttpStatus.NOT_FOUND);
        }
    }
}


// GET /companies/{companyId}/reviews_
// POST /companies/{companyId}/reviews
// GET /companies/{companyId}/reviews/{reviewId}
// PUT/companies/{companyId}/reviews/{reviewId}
// DELETE /companies/{companyId}/reviews/{reviewId}