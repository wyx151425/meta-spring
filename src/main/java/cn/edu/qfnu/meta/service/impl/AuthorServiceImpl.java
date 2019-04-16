package cn.edu.qfnu.meta.service.impl;

import cn.edu.qfnu.meta.repository.UserRepository;
import cn.edu.qfnu.meta.model.domain.User;
import cn.edu.qfnu.meta.service.AuthorService;
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
