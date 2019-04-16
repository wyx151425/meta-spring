package cn.edu.qfnu.meta.service.impl;

import cn.edu.qfnu.meta.model.domain.Review;
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

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
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
