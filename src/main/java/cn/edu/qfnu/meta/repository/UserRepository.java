package cn.edu.qfnu.meta.repository;

import cn.edu.qfnu.meta.model.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户类DAO
 *
 * @author 王振琦
 * createAt: 2018/08/01
 * updateAt: 2018/11/09
 */
@Repository(value = "userRepository")
public interface UserRepository {
    /**
     * 保存用户对象
     *
     * @param user 用户对象
     */
    void save(User user);

    /**
     * 更新用户对象
     *
     * @param user 用户对象
     */
    void update(User user);

    /**
     * 根据ID查询用户对象
     *
     * @param id 用户ID
     * @return 用户数据对象
     */
    User findUserById(Integer id);

    /**
     * 根据手机号获取用户
     *
     * @param mobilePhoneNumber 手机号
     * @return 用户数据对象
     */
    User findUserByMobilePhoneNumber(String mobilePhoneNumber);

    /**
     * 根据邮箱获取用户
     *
     * @param email 邮箱
     * @return 用户数据对象
     */
    User findUserByEmail(String email);

    /**
     * 获取漫画家对象
     *
     * @param id 漫画家对象ID
     * @return 漫画家对象
     */
    User findAuthorById(Integer id);

    /**
     * 获取用户对象集合并且包含用户的漫画册对象集合
     *
     * @return 用户对象集合
     */
    List<User> findAuthorList();

    /**
     * 获取用户对象集合并且包含用户的漫画册对象集合
     *
     * @param limit 限制
     * @return 用户对象集合
     */
    List<User> findAuthorListWithLimit(int limit);

    /**
     * 获取用户对象集合并且包含用户的漫画册对象集合
     *
     * @param index 分页索引
     * @param limit 限制
     * @return 用户对象集合
     */
    List<User> findAuthorListDescByFollowerWithLimit(@Param(value = "index") int index, @Param(value = "limit") int limit);
}
