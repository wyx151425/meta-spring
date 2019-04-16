package cn.edu.qfnu.meta.model.domain;

/**
 * 漫画分页类
 *
 * @author 王振琦
 * createAt: 2018/08/01
 * updateAt: 2018/10/10
 */
public class Page extends MetaEntity {
    /**
     * 所属漫画册
     */
    private Book book;
    private Integer bookId;
    /**
     * 分页名称
     */
    private String name;
    /**
     * 图像URL
     */
    private String image;

    public Page() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Page{" +
                "book=" + book +
                ", image='" + image + '\'' +
                '}';
    }
}
