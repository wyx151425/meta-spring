package cn.edu.qfnu.meta.repository;

import cn.edu.qfnu.meta.model.domain.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限数据仓库
 *
 * @author 王振琦
 * createAt: 2019/01/13
 * updateAt: 2019/01/13
 */
@Repository(value = "permissionRepository")
public interface PermissionRepository {
    /**
     * 保存权限
     *
     * @param permission 权限对象
     */
    void save(Permission permission);

    /**
     * 根据角色查询权限
     *
     * @param role 角色
     * @return 权限数据集合
     */
    List<Permission> findAllByRole(String role);
}
