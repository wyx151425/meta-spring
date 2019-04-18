package cn.edu.qfnu.meta.service.impl;

import cn.edu.qfnu.meta.model.domain.Permission;
import cn.edu.qfnu.meta.model.domain.User;
import cn.edu.qfnu.meta.model.dto.UserPwd;
import cn.edu.qfnu.meta.repository.PermissionRepository;
import cn.edu.qfnu.meta.repository.UserRepository;
import cn.edu.qfnu.meta.service.UserService;
import cn.edu.qfnu.meta.util.Generator;
import cn.edu.qfnu.meta.util.StatusCode;
import cn.edu.qfnu.meta.context.exception.MetaException;
import cn.edu.qfnu.meta.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户业务逻辑类
 *
 * @author 王振琦
 * createAt: 2018/08/01
 * updateAt: 2018/10/10
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public User login(User user) {
        // 根据手机号检查用户是否存在
        User targetUser = userRepository.findUserByMobilePhoneNumber(user.getMobilePhoneNumber());
        if (null == targetUser) {
            throw new MetaException(StatusCode.USER_UNREGISTER);
        } else {
            // 检查用户的账号是否处于被禁用的状态
            if (Constant.UserStatus.DISABLED == targetUser.getStatus()) {
                throw new MetaException(StatusCode.USER_DISABLED);
            } else {
                // 校验用户的密码是否正确
                if (targetUser.getPassword().equals(user.getPassword())) {
                    // 密码校验通过后，加载用户角色
                    Map<String, Boolean> roles = new HashMap<>();
                    roles.put(targetUser.getRole(), true);
                    targetUser.setRoles(roles);
                    return targetUser;
                } else {
                    throw new MetaException(StatusCode.USER_PASSWORD_ERROR);
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User register(User user) {
        User targetUser = userRepository.findUserByMobilePhoneNumber(user.getMobilePhoneNumber());
        if (null == targetUser) {
            user.setObjectId(Generator.getObjectId());
            user.setStatus(Constant.UserStatus.USER);
            user.setAvatar("../images/default-avatar.jpg");
            user.setPortrait("../images/default-portrait.jpg");
            user.setGender("保密");
            user.setFollow(0);
            user.setFollower(0);
            user.setFavorite(0);
            user.setBook(0);
            user.setRole(Constant.Roles.USER);
            userRepository.save(user);
            return user;
        } else {
            throw new MetaException(StatusCode.USER_REGISTERED);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User update(User user) {
        userRepository.update(user);
        return userRepository.findUserById(user.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User updateEmail(User user) {
        User targetUser = userRepository.findUserById(user.getId());
        if (targetUser.getPassword().equals(user.getPassword())) {
            targetUser.setEmail(user.getEmail());
            userRepository.update(targetUser);
            return targetUser;
        } else {
            throw new MetaException(StatusCode.USER_PASSWORD_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User updatePassword(UserPwd userPwd) {
        User targetUser = userRepository.findUserById(userPwd.getId());
        if (targetUser.getPassword().equals(userPwd.getOldPassword())) {
            targetUser.setPassword(userPwd.getNewPassword());
            userRepository.update(targetUser);
            return targetUser;
        } else {
            throw new MetaException(StatusCode.USER_PASSWORD_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User updateUserAvatar(User user) {
        User targetUser = userRepository.findUserById(user.getId());
        targetUser.setAvatar(user.getAvatar());
        userRepository.update(targetUser);
        return targetUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User updateUserPortrait(User user) {
        User targetUser = userRepository.findUserById(user.getId());
        targetUser.setPortrait(user.getPortrait());
        userRepository.update(targetUser);
        return targetUser;
    }

    @Override
    public void authorizeUser(User user) {
        user.setRole(Constant.Roles.LECTURER);
        user.setStatus(Constant.UserStatus.AUTHOR);
        userRepository.update(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public User findUserContainsBookList(Integer id) {
        User user = userRepository.findUserById(id);
        user.getBookList();
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public User findUserByMobilePhoneNumber(String mobilePhoneNumber) {
        return userRepository.findUserByMobilePhoneNumber(mobilePhoneNumber);
    }
}
