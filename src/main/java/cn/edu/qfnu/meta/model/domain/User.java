package cn.edu.qfnu.meta.model.domain;

import java.util.List;
import java.util.Map;

/**
 * 用户类
 *
 * @author 王振琦
 * createAt: 2018/08/01
 * updateAt: 2018/10/10
 */
public class User extends MetaEntity {
    /**
     * 角色
     */
    private String role;
    /**
     * 权限键值对
     */
    private Map<String, Boolean> permissions;
    /**
     * 角色键值
     */
    private Map<String, Boolean> roles;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String mobilePhoneNumber;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态：0-禁用用户/1-普通用户/2-普通可发布漫画用户
     * private Integer status;
     */
    /**
     * 邮箱
     */
    private String email;
    /**
     * 座右铭
     */
    private String motto;
    /**
     * 简介
     */
    private String profile;
    /**
     * 性别
     */
    private String gender;
    /**
     * 关注作家数
     */
    private Integer follow;
    /**
     * 粉丝数
     */
    private Integer follower;
    /**
     * 收藏漫画册数
     */
    private Integer favorite;
    /**
     * 漫画册数量
     */
    private Integer book;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 个人肖像
     */
    private String portrait;

    private List<Book> bookList;

    public User() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Map<String, Boolean> getRoles() {
        return roles;
    }

    public void setRoles(Map<String, Boolean> roles) {
        this.roles = roles;
    }

    public Map<String, Boolean> getPermissions() {
        return permissions;
    }

    public void setPermissions(Map<String, Boolean> permissions) {
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getFollow() {
        return follow;
    }

    public void setFollow(Integer follow) {
        this.follow = follow;
    }

    public Integer getFollower() {
        return follower;
    }

    public void setFollower(Integer follower) {
        this.follower = follower;
    }

    public Integer getFavorite() {
        return favorite;
    }

    public void setFavorite(Integer favorite) {
        this.favorite = favorite;
    }

    public Integer getBook() {
        return book;
    }

    public void setBook(Integer book) {
        this.book = book;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", mobilePhoneNumber='" + mobilePhoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", motto='" + motto + '\'' +
                ", profile='" + profile + '\'' +
                ", gender='" + gender + '\'' +
                ", follow=" + follow +
                ", follower=" + follower +
                ", favorite=" + favorite +
                ", book=" + book +
                ", avatar='" + avatar + '\'' +
                ", portrait='" + portrait + '\'' +
                '}';
    }
}
