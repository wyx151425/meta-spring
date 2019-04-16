package cn.edu.qfnu.meta.service;

import cn.edu.qfnu.meta.model.domain.Book;
import cn.edu.qfnu.meta.model.domain.Favorite;

import java.util.List;

/**
 * 收藏业务逻辑规范
 *
 * @author 王振琦
 * createAt: 2018/11/07
 * updateAt: 2019/01/08
 */
public interface FavoriteService {
    /**
     * 保存用户收藏关系对象
     *
     * @param favorite 用户收藏关系对象
     */
    void saveFavorite(Favorite favorite);

    /**
     * 删除用户收藏关系对向
     *
     * @param id 用户收藏关系对象的ID
     */
    void deleteFavorite(Integer id);

    /**
     * 根据漫画册和用户查找收藏关系对象
     *
     * @param bookId 漫画册ID
     * @param userId 用户ID
     * @return 收藏关系对象
     */
    Favorite findFavoriteByBookAndUser(Integer bookId, Integer userId);

    /**
     * 获取收藏的漫画册数据集合
     *
     * @param userId 用户ID
     * @return 漫画册数据集合
     */
    List<Book> findFavoriteBookList(Integer userId);
}
