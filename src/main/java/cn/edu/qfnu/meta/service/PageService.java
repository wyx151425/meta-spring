package cn.edu.qfnu.meta.service;

import cn.edu.qfnu.meta.model.domain.Page;

import java.util.List;

/**
 * 漫画分页业务逻辑接口
 *
 * @author 王振琦
 * createAt: 2018/08/01
 * updateAt: 2018/10/10
 */
public interface PageService {
    /**
     * 保存漫画分页
     *
     * @param page 漫画分页数据对象
     */
    void save(Page page);

    /**
     * 删除漫画分页
     *
     * @param id 漫画分页ID
     */
    void delete(Integer id);

    /**
     * 更新漫画分页
     *
     * @param page 漫画分页数据对象
     */
    void update(Page page);

    /**
     * 根据漫画分页ID获取漫画分页对象及其所属漫画册对象
     *
     * @param id 漫画分页ID
     * @return 包含漫画册对象的漫画分页对象
     */
    Page findPageContainsBook(Integer id);

    /**
     * 根据漫画册ID获取其包含的漫画分页
     *
     * @param bookId 漫画册ID
     * @return 漫画分页对象集合
     */
    List<Page> findPageListByBook(Integer bookId);
}
