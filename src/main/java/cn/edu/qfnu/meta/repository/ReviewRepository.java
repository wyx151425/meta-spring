package cn.edu.qfnu.meta.repository;

import cn.edu.qfnu.meta.model.domain.Review;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author NiPing
 */
@Repository(value = "reviewRepository")
public interface ReviewRepository {
    /**
     * 保存试题对象
     *
     * @param review 试题对象
     */
    void save(Review review);

    /**
     * 根据课程查询测评试题
     *
     * @param courseId 课程ID
     * @return 测评试题数据集
     */
    List<Review> findReviewsByCourse(Integer courseId);
}
