package cn.edu.qfnu.meta;

import cn.edu.qfnu.meta.repository.PermissionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MetaApplicationTests {

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    public void contextLoads() {
    }

//    @Test
//    public void addPermission() {
//        Permission permission1 = new Permission();
//        permission1.setObjectId(Generator.getObjectId());
//        permission1.setStatus(Constant.Status.GENERAL);
//        permission1.setRole(Constant.Roles.USER);
//        permission1.setCode(Constant.Permissions.COMMON);
//        permissionRepository.save(permission1);
//
//        Permission permission2 = new Permission();
//        permission2.setObjectId(Generator.getObjectId());
//        permission2.setStatus(Constant.Status.GENERAL);
//        permission2.setRole(Constant.Roles.LECTURER);
//        permission2.setCode(Constant.Permissions.CREATE_COURSE);
//        permissionRepository.save(permission2);
//
//        Permission permission3 = new Permission();
//        permission3.setObjectId(Generator.getObjectId());
//        permission3.setStatus(Constant.Status.GENERAL);
//        permission3.setRole(Constant.Roles.LECTURER);
//        permission3.setCode(Constant.Permissions.QUERY_COURSE);
//        permissionRepository.save(permission3);
//
//        Permission permission4 = new Permission();
//        permission4.setObjectId(Generator.getObjectId());
//        permission4.setStatus(Constant.Status.GENERAL);
//        permission4.setRole(Constant.Roles.LECTURER);
//        permission4.setCode(Constant.Permissions.QUERY_FOLLOWER);
//        permissionRepository.save(permission4);
//
//        Permission permission5 = new Permission();
//        permission5.setObjectId(Generator.getObjectId());
//        permission5.setStatus(Constant.Status.GENERAL);
//        permission5.setRole(Constant.Roles.MANAGER);
//        permission5.setCode(Constant.Permissions.MANAGE_SYSTEM);
//        permissionRepository.save(permission5);
//
//        Permission permission6 = new Permission();
//        permission6.setObjectId(Generator.getObjectId());
//        permission6.setStatus(Constant.Status.GENERAL);
//        permission6.setRole(Constant.Roles.MANAGER);
//        permission6.setCode(Constant.Permissions.QUERY_USER);
//        permissionRepository.save(permission6);
//
//        Permission permission7 = new Permission();
//        permission7.setObjectId(Generator.getObjectId());
//        permission7.setStatus(Constant.Status.GENERAL);
//        permission7.setRole(Constant.Roles.MANAGER);
//        permission7.setCode(Constant.Permissions.AUTHORIZATION);
//        permissionRepository.save(permission7);
//    }
}
