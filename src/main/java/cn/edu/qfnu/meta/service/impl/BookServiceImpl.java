package cn.edu.qfnu.meta.service.impl;

import cn.edu.qfnu.meta.repository.UserRepository;
import cn.edu.qfnu.meta.model.domain.Book;
import cn.edu.qfnu.meta.model.domain.User;
import cn.edu.qfnu.meta.repository.BookRepository;
import cn.edu.qfnu.meta.repository.PageRepository;
import cn.edu.qfnu.meta.service.BookService;
import cn.edu.qfnu.meta.util.Constant;
import cn.edu.qfnu.meta.util.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 漫画册业务逻辑类
 *
 * @author 王振琦
 * createAt: 2018/08/01
 * updateAt: 2018/11/09
 */
@Service(value = "bookService")
public class BookServiceImpl implements BookService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final PageRepository pageRepository;

    @Autowired
    public BookServiceImpl(UserRepository userRepository, BookRepository bookRepository, PageRepository pageRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.pageRepository = pageRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBook(Book book) {
        book.setObjectId(Generator.getObjectId());
        book.setStatus(Constant.BookStatus.CREATED);
        book.setFavor(0);
        book.setPage(0);
        bookRepository.save(book);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBook(Book book) {
        bookRepository.update(book);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void publishBook(Integer id) {
        Book book = bookRepository.findBookById(id);
        book.setPublish(true);
        bookRepository.update(book);

        User user = book.getAuthor();
        int bookQuantity = user.getBook();
        user.setBook(++bookQuantity);
        userRepository.update(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBook(Integer id) {
        bookRepository.delete(id);
        pageRepository.deleteAllByBookId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Book> findBookList() {
        return bookRepository.findBookList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Book> findBookListByAuthor(Integer authorId) {
        return bookRepository.findBookListByAuthorId(authorId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Book> findPublishedBookListByAuthor(Integer authorId) {
        return bookRepository.findPublishedBookListByAuthorId(authorId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Book findBookContainsAuthorAndPageList(Integer id) {
        Book book = bookRepository.findBookById(id);
        book.getAuthor();
        book.getPageList();
        return book;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Book> findBookListByRank(int index) {
        return bookRepository.findBookListDescByFavorWithLimit(index, 8);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Book> findBookListByStyleAndType(String style, String type) {
        return bookRepository.findBookListByStyleAndTypeWithLimit(0, Constant.PageModel.Limit.BOOK, style, type);
    }
}
