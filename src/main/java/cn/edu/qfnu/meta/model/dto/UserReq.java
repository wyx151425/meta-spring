package cn.edu.qfnu.meta.model.dto;

import cn.edu.qfnu.meta.model.domain.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 用于封装前端请求数据的用户类
 *
 * @author 王振琦
 * createAt: 2018/08/02
 * updateAt: 2018/10/08
 */
public class UserReq {
    /**
     * 注册验证接口
     */
    public interface Register {
    }

    /**
     * 登录验证接口
     */
    public interface Login {
    }

    /**
     * 姓名
     */
    @NotNull(groups = {UserReq.Register.class})
    @Length(min = 2, max = 3, groups = {UserReq.Register.class})
    private String name;
    /**
     * 手机号
     */
    @NotNull(groups = {UserReq.Register.class, UserReq.Login.class})
    @Length(min = 11, max = 11, groups = {UserReq.Register.class, UserReq.Login.class})
    @Pattern(regexp = "((13\\d)|(15\\d)|(17\\d)|(18\\d))\\d{8}", groups = {UserReq.Register.class, UserReq.Login.class})
    private String mobilePhoneNumber;
    /**
     * 密码
     */
    @NotEmpty(groups = {UserReq.Register.class, UserReq.Login.class})
    @Length(min = 6, max = 32, groups = {UserReq.Register.class, UserReq.Login.class})
    private String password;

    private String oldPassword;
    private String newPassword;

    public UserReq() {
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public User getRegisterUser() {
        User user = new User();
        user.setName(name);
        user.setMobilePhoneNumber(mobilePhoneNumber);
        user.setPassword(password);
        return user;
    }

    public User getLoginUser() {
        User user = new User();
        user.setMobilePhoneNumber(mobilePhoneNumber);
        user.setPassword(password);
        return user;
    }

    public UserPwd getPwdUser() {
        UserPwd userPwd = new UserPwd();
        userPwd.setOldPassword(oldPassword);
        userPwd.setNewPassword(newPassword);
        return userPwd;
    }
}
