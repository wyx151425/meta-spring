package cn.edu.qfnu.meta.repository;

import cn.edu.qfnu.meta.model.domain.Book;
import cn.edu.qfnu.meta.model.domain.Favorite;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户收藏信息数据仓库
 *
 * @author 王振琦
 * createAt: 2018/11/07
 * updateAt: 2019/01/08
 */
@Repository(value = "favoriteRepository")
public interface FavoriteRepository {
    /**
     * 保存用户收藏
     *
     * @param favorite 用户收藏信息对象
     */
    void save(Favorite favorite);

    /**
     * 根据ID删除用户收藏
     *
     * @param id 用户收藏信息对象的ID
     */
    void delete(Integer id);

    /**
     * 根据ID获取收藏关系对象
     *
     * @param id 收藏关系对象ID
     * @return 收藏关系对象
     */
    Favorite findFavoriteById(Integer id);

    /**
     * 根据漫画册ID和用户ID获取收藏关系对象
     *
     * @param bookId 漫画册ID
     * @param userId 用户ID
     * @return 收藏关系对象
     */
    Favorite findFavoriteByBookIdAndUserId(
            @Param(value = "bookId") Integer bookId, @Param(value = "userId") Integer userId
    );

    /**
     * 查找收藏的漫画册
     *
     * @param userId 用户ID
     * @return 收藏的漫画册数据集合
     */
    List<Book> findFavoriteBookList(Integer userId);
}
