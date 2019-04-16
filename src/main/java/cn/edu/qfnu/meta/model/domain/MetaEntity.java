package cn.edu.qfnu.meta.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 应用实体类统一父类
 *
 * @author 王振琦
 * createAt: 2018/08/01
 * updateAt: 2018/10/10
 *
 * 注解JsonIgnoreProperties，表示：
 * 在JSON序列化的过程中，忽略Bean中的一些属性序列化和反序列化时抛出的异常
 */
@JsonIgnoreProperties(value = {"handler"})
public class MetaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 对象ID
     */
    private Integer id;
    /**
     * 对象UUID
     */
    private String objectId;
    /**
     * 对象状态标识
     */
    private Integer status;
    /**
     * 创建时间
     */
    private LocalDateTime createAt;
    /**
     * 更新时间
     */
    private LocalDateTime updateAt;

    public MetaEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
