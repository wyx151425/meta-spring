package cn.edu.qfnu.meta.service.impl;

import cn.edu.qfnu.meta.context.exception.MetaException;
import cn.edu.qfnu.meta.repository.UserRepository;
import cn.edu.qfnu.meta.model.domain.User;
import cn.edu.qfnu.meta.service.AuthorService;
import cn.edu.qfnu.meta.util.Constant;
import cn.edu.qfnu.meta.util.Generator;
import cn.edu.qfnu.meta.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 漫画册作者业务逻辑实现
 *
 * @author 王振琦
 * createAt: 2018/11/06
 * updateAt: 2018/11/09
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    private final UserRepository userRepository;

    @Autowired
    public AuthorServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        User targetUser = userRepository.findUserByMobilePhoneNumber(user.getMobilePhoneNumber());
        if (null == targetUser) {
            user.setObjectId(Generator.getObjectId());
            user.setStatus(Constant.UserStatus.AUTHOR);
            user.setAvatar("../images/default-avatar.jpg");
            user.setPortrait("../images/default-portrait.jpg");
            user.setGender("保密");
            user.setFollow(0);
            user.setFollower(0);
            user.setFavorite(0);
            user.setBook(0);
            user.setRole(Constant.Roles.LECTURER);
            userRepository.save(user);
            return user;
        } else {
            throw new MetaException(StatusCode.USER_REGISTERED);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public User findAuthorContainsBookList(Integer id) {
        User user = userRepository.findAuthorById(id);
        user.getBookList();
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<User> findAuthorList() {
        List<User> authorList = userRepository.findAuthorList();
        for (User author : authorList) {
            author.getBookList();
        }
        return authorList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<User> findAuthorListForIndex() {
        return userRepository.findAuthorListWithLimit(5);
    }


    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<User> findLecturerListByRank(int index) {
        return userRepository.findAuthorListDescByFollowerWithLimit(index, 8);
    }
}
