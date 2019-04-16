package cn.edu.qfnu.meta.service.impl;

import cn.edu.qfnu.meta.model.domain.Book;
import cn.edu.qfnu.meta.model.domain.Favorite;
import cn.edu.qfnu.meta.model.domain.User;
import cn.edu.qfnu.meta.repository.FavoriteRepository;
import cn.edu.qfnu.meta.repository.UserRepository;
import cn.edu.qfnu.meta.service.FavoriteService;
import cn.edu.qfnu.meta.util.Constant;
import cn.edu.qfnu.meta.util.Generator;
import cn.edu.qfnu.meta.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 收藏业务逻辑实现
 *
 * @author 王振琦
 * createAt: 2018/11/07
 * updateAt: 2019/01/08
 */
@Service(value = "favoriteService")
public class FavoriteServiceImpl implements FavoriteService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final FavoriteRepository favoriteRepository;

    @Autowired
    public FavoriteServiceImpl(
            UserRepository userRepository, BookRepository bookRepository, FavoriteRepository favoriteRepository
    ) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveFavorite(Favorite favorite) {
        favorite.setObjectId(Generator.getObjectId());
        favorite.setStatus(Constant.Status.GENERAL);
        favoriteRepository.save(favorite);
        Book book = bookRepository.findBookById(favorite.getBook().getId());
        int star = book.getFavor();
        book.setFavor(++star);
        bookRepository.update(book);
        User user = userRepository.findUserById(favorite.getUser().getId());
        int favor = user.getFavorite();
        user.setFavorite(++favor);
        userRepository.update(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFavorite(Integer id) {
        Favorite favorite = favoriteRepository.findFavoriteById(id);
        favoriteRepository.delete(id);
        Book book = bookRepository.findBookById(favorite.getBookId());
        int star = book.getFavor();
        book.setFavor(--star);
        bookRepository.update(book);
        User user = userRepository.findUserById(favorite.getUserId());
        int favor = user.getFavorite();
        user.setFavorite(--favor);
        userRepository.update(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Favorite findFavoriteByBookAndUser(Integer bookId, Integer userId) {
        return favoriteRepository.findFavoriteByBookIdAndUserId(bookId, userId);
    }

    @Override
    public List<Book> findFavoriteBookList(Integer userId) {
        return favoriteRepository.findFavoriteBookList(userId);
    }
}
