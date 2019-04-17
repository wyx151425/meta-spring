package cn.edu.qfnu.meta.model.domain;

import cn.edu.qfnu.meta.util.Constant;
import cn.edu.qfnu.meta.util.Generator;

/**
 * @author NiPing
 */
public class Result extends MetaEntity {
    /**
     * 做题人
     */
    private User user;
    /**
     * 做题人ID
     */
    private Integer userId;
    /**
     * 课程
     */
    private Book book;
    /**
     * 课程ID
     */
    private Integer bookId;
    /**
     * 得分
     */
    private Double ratio;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public static Result getInstance() {
        Result result = new Result();
        result.setObjectId(Generator.getObjectId());
        result.setStatus(Constant.Status.GENERAL);
        return result;
    }
}
