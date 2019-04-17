package cn.edu.qfnu.meta.service.impl;

import cn.edu.qfnu.meta.model.domain.Book;
import cn.edu.qfnu.meta.model.domain.Result;
import cn.edu.qfnu.meta.model.domain.Review;
import cn.edu.qfnu.meta.model.domain.User;
import cn.edu.qfnu.meta.repository.ResultRepository;
import cn.edu.qfnu.meta.repository.ReviewRepository;
import cn.edu.qfnu.meta.service.ReviewService;
import cn.edu.qfnu.meta.util.Constant;
import cn.edu.qfnu.meta.util.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author NiPing
 */
@Service(value = "reviewService")
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ResultRepository resultRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ResultRepository resultRepository) {
        this.reviewRepository = reviewRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveReview(Review review) {
        StringBuilder options = new StringBuilder();
        int size = review.getOptionList().size();
        for (int index = 0; index < size; index++) {
            options.append(review.getOptionList().get(index));
            if (index != size - 1) {
                options.append(",");
            }
        }
        review.setObjectId(Generator.getObjectId());
        review.setStatus(Constant.Status.GENERAL);
        review.setOptions(options.toString());
        reviewRepository.save(review);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteReview(Integer id) {
        reviewRepository.delete(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitReviews(User user, List<Review> reviews) {
        int quantity = 0;
        double trueQuantity = 0.0;
        for (Review review : reviews) {
            quantity++;
            if (review.getAnswer().equals(review.getChoice())) {
                trueQuantity += 1;
            }
        }
        double ratio = trueQuantity / quantity;
        Result result = Result.getInstance();
        result.setRatio(ratio);
        result.setUser(user);
        Book book = new Book();
        book.setId(reviews.get(0).getBookId());
        result.setBook(book);
        resultRepository.save(result);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Review> findReviewsByCourse(Integer courseId) {
        List<Review> reviews = reviewRepository.findReviewsByCourse(courseId);
        for (Review review : reviews) {
            String[] options = review.getOptions().split(",");
            review.setOptionList(Arrays.asList(options));
            review.setChoice("");
            review.setComplete(false);
        }
        return reviews;
    }
}
