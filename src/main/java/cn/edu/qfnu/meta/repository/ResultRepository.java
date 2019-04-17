package cn.edu.qfnu.meta.repository;

import cn.edu.qfnu.meta.model.domain.Result;
import org.springframework.stereotype.Repository;

/**
 * @author NiPing
 */
@Repository(value = "resultRepository")
public interface ResultRepository {
    /**
     * 保存测试结果
     *
     * @param result 试结果对象
     */
    void save(Result result);
}
