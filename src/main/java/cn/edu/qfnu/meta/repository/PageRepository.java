package cn.edu.qfnu.meta.repository;

import cn.edu.qfnu.meta.model.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 漫画分页类DAO
 *
 * @author 王振琦
 * createAt: 2018/08/01
 * updateAt: 2018/11/09
 */
@Repository(value = "pageRepository")
public interface PageRepository {
    /**
     * 保存漫画分页
     *
     * @param page 漫画分页对象
     */
    void save(Page page);

    /**
     * 删除漫画分页
     *
     * @param id 漫画分页数据ID
     */
    void delete(Integer id);

    /**
     * 删除课程下的全部章节
     *
     * @param id 课程数据ID
     */
    void deleteAllByBookId(Integer id);

    /**
     * 更新漫画分页
     *
     * @param page 漫画分页对象
     */
    void update(Page page);

    /**
     * 根据漫画分页ID获取漫画分页对象及其所属漫画册对象
     *
     * @param id 漫画分页ID
     * @return 包含漫画册对象的漫画分页对象
     */
    Page findPageById(Integer id);

    /**
     * 根据漫画册ID和状态标识获取漫画册的漫画分页
     *
     * @param bookId 漫画册ID
     * @return 漫画分页集合
     */
    List<Page> findPageListByBookId(Integer bookId);
}
