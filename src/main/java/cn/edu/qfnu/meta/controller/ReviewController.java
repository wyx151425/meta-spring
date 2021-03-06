package cn.edu.qfnu.meta.controller;

import cn.edu.qfnu.meta.model.domain.Result;
import cn.edu.qfnu.meta.model.domain.Review;
import cn.edu.qfnu.meta.model.dto.Response;
import cn.edu.qfnu.meta.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author NiPing
 */
@RestController
@RequestMapping(value = "api")
public class ReviewController extends MetaFacade {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(value = "reviews")
    public Response<Review> actionSaveReview(@RequestBody Review review) {
        reviewService.saveReview(review);
        return new Response<>(review);
    }

    @GetMapping(value = "courses/{courseId}/reviews")
    public Response<List<Review>> actionQueryReviewByCourse(@PathVariable(value = "courseId") Integer id) {
        List<Review> reviews = reviewService.findReviewsByCourse(id);
        return new Response<>(reviews);
    }

    @DeleteMapping(value = "reviews/{id}")
    public Response<Review> actionDeleteReview(@PathVariable(value = "id") Integer id) {
        reviewService.deleteReview(id);
        return new Response<>();
    }

    @PostMapping(value = "reviews/submit")
    public Response<Result> actionSubmitReviews(@RequestBody List<Review> reviews) {
        reviewService.submitReviews(getCurrentUser(), reviews);
        return new Response<>();
    }
}
