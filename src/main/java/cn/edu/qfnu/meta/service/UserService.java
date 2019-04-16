package cn.edu.qfnu.meta.service;

import cn.edu.qfnu.meta.model.domain.User;
import cn.edu.qfnu.meta.model.dto.UserPwd;

/**
 * 用户业务逻辑接口
 *
 * @author 王振琦
 * createAt: 2018/08/01
 * updateAt: 2019/01/14
 */
public interface UserService {
    /**
     * 用户登录方法
     *
     * @param user 封装前端提交数据的用户对象
     * @return 带有主键的用户对象
     */
    User login(User user);

    /**
     * 用户注册方法
     *
     * @param user 封装前端提交数据的用户对象
     * @return 保存后的用户对象
     */
    User register(User user);

    /**
     * 用户信息更新方法
     *
     * @param user 封装前端提交数据的用户对象
     * @return 更新后的用户对象
     */
    User update(User user);

    /**
     * 用户邮箱更新方法
     *
     * @param user 封装前端提交数据的用户对象
     * @return 更新后的用户对象
     */
    User updateEmail(User user);

    /**
     * 用户密码更新方法
     *
     * @param userPwd 封装用户数据的对象
     * @return 更新后的用户对象
     */
    User updatePassword(UserPwd userPwd);

    /**
     * 更新用户的头像
     *
     * @param user 封装了用户ID和新头像路径的用户对象
     * @return 更新后的用户对象
     */
    User updateUserAvatar(User user);

    /**
     * 更新用户的肖像
     *
     * @param user 封装了用户ID和新肖像路径的用户对象
     * @return 更新后的用户对象
     */
    User updateUserPortrait(User user);

    /**
     * 为用户授予讲师角色
     *
     * @param user 用户对象
     */
    void authorizeUser(User user);

    /**
     * 根据用户ID查询用户对象
     *
     * @param id 用户ID
     * @return 用户对象
     */
    User findUserContainsBookList(Integer id);

    /**
     * 根据手机号获取用户对象
     *
     * @param mobilePhoneNumber 手机号
     * @return 用户对象
     */
    User findUserByMobilePhoneNumber(String mobilePhoneNumber);
}
