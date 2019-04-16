package cn.edu.qfnu.meta.model.dto;

/**
 * 用于用户修改密码的数据传输类
 *
 * @author 王振琦
 * createAt: 2018/10/08
 * updateAt: 2018/10/08
 */
public class UserPwd {
    /**
     * 用户ID
     */
    private Integer id;
    /**
     * 旧密码
     */
    private String oldPassword;
    /**
     * 新密码
     */
    private String newPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
