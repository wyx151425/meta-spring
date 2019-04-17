package cn.edu.qfnu.meta.service;

import cn.edu.qfnu.meta.model.domain.Review;
import cn.edu.qfnu.meta.model.domain.User;

import java.util.List;

/**
 * 试题业务对象类
 *
 * @author NiPing
 */
public interface ReviewService {
    /**
     * 保存试题
     *
     * @param review 试题对象
     */
    void saveReview(Review review);

    /**
     * 删除试题
     *
     * @param id 试题对象的ID
     */
    void deleteReview(Integer id);

    /**
     * 提交测评
     *
     * @param user    做题人
     * @param reviews 测评题目
     */
    void submitReviews(User user, List<Review> reviews);

    /**
     * 根据课程查询课后测评试题
     *
     * @param courseId 课程ID
     * @return 课后测评试题数据集
     */
    List<Review> findReviewsByCourse(Integer courseId);
}
