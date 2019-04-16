package cn.edu.qfnu.meta.model.domain;

/**
 * 权限类
 *
 * @author 王振琦
 * createAt: 2019/01/13
 * updateAt: 2019/01/13
 */
public class Permission extends MetaEntity {
    /**
     * 权限所属角色
     */
    private String role;
    /**
     * 权限字符串
     */
    private String code;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
