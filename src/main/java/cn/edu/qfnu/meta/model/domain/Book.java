package cn.edu.qfnu.meta.model.domain;

import java.util.List;

/**
 * 漫画册类
 *
 * @author 王振琦
 * createAt: 2018/08/01
 * updateAt: 2018/10/10
 */
public class Book extends MetaEntity {
    /**
     * status 0-删除 1-新建未审核 2-修改未审核 3-审核通过允许展示
     */
    /**
     * 所属漫画作者
     */
    private User author;
    private Integer authorId;
    /**
     * 漫画册名称
     */
    private String name;
    /**
     * 漫画册所属风格
     */
    private String style;
    /**
     * 漫画册类型
     */
    private String type;
    /**
     * 漫画册简介
     */
    private String description;
    /**
     * 漫画册封面路径
     */
    private String cover;
    /**
     * 漫画册漫画分页数
     */
    private Integer page;
    /**
     * 收藏此漫画的用户数
     */
    private Integer favor;
    /**
     * 发布标识
     */
    private Boolean publish;

    private List<Page> pageList;

    public Book() {
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getFavor() {
        return favor;
    }

    public void setFavor(Integer favor) {
        this.favor = favor;
    }

    public Boolean getPublish() {
        return publish;
    }

    public void setPublish(Boolean publish) {
        this.publish = publish;
    }

    public List<Page> getPageList() {
        return pageList;
    }

    public void setPageList(List<Page> pageList) {
        this.pageList = pageList;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author=" + author +
                ", name='" + name + '\'' +
                ", style='" + style + '\'' +
                ", description='" + description + '\'' +
                ", cover='" + cover + '\'' +
                ", page=" + page +
                ", favor=" + favor +
                ", type=" + type +
                '}';
    }
}
