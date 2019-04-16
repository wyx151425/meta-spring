package cn.edu.qfnu.meta.model.domain;

/**
 * 收藏类
 *
 * @author 王振琦
 * createAt: 2018/11/06
 * updateAt: 2018/11/06
 */
public class Favorite extends MetaEntity {
    /**
     * 被收藏的漫画册
     */
    private Book book;
    /**
     * 收藏漫画册的用户
     */
    private User user;
    /**
     * 被收藏的漫画册的ID
     */
    private Integer bookId;
    /**
     * 收藏漫画册的用户的ID
     */
    private Integer userId;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
