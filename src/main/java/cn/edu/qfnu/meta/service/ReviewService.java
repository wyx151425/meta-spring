package cn.edu.qfnu.meta.service;

import cn.edu.qfnu.meta.model.domain.Review;

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
     * 根据课程查询课后测评试题
     *
     * @param courseId 课程ID
     * @return 课后测评试题数据集
     */
    List<Review> findReviewsByCourse(Integer courseId);
}
