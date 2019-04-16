package cn.edu.qfnu.meta.model.domain;

import java.util.List;

/**
 * @author NiPing
 */
public class Review extends MetaEntity {
    /**
     * 所属课程的ID
     */
    private Integer bookId;
    /**
     * 所属课程
     */
    private Book book;
    /**
     * 问题
     */
    private String question;
    /**
     * 选项拼接字符串
     */
    private String options;
    /**
     * 选项分割字符串集合
     */
    private List<String> optionList;
    /**
     * 是否单选
     */
    private Boolean single;
    /**
     * 答案拼接字符串
     */
    private String answer;

    public Review() {
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public List<String> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<String> optionList) {
        this.optionList = optionList;
    }

    public Boolean getSingle() {
        return single;
    }

    public void setSingle(Boolean single) {
        this.single = single;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
