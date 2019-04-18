package cn.edu.qfnu.meta.controller;

import cn.edu.qfnu.meta.model.domain.User;
import cn.edu.qfnu.meta.model.dto.Response;
import cn.edu.qfnu.meta.model.dto.UserPwd;
import cn.edu.qfnu.meta.model.dto.UserReq;
import cn.edu.qfnu.meta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户业务API
 *
 * @author 王振琦
 * createAt: 2018/08/01
 * updateAt: 2019/01/08
 */
@RestController
@RequestMapping(value = "api")
public class UserController extends MetaFacade {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "users/login")
    public Response<User> actionUserLogin(
            @Validated(UserReq.Login.class) @RequestBody UserReq requestUser,
            BindingResult result
    ) {
        bindingResultInspect(result);
        User user = userService.login(requestUser.getLoginUser());
        addCurrentUser(user);
        return new Response<>(user);
    }

    @PostMapping(value = "users/logout")
    public Response<User> actionUserLogout() {
        removeCurrentUser();
        return new Response<>();
    }

    @PostMapping(value = "users/register")
    public Response<User> actionUserRegister(
            @Validated(UserReq.Register.class) @RequestBody UserReq requestUser,
            BindingResult result
    ) {
        bindingResultInspect(result);
        User user = userService.register(requestUser.getRegisterUser());
        addCurrentUser(user);
        return new Response<>(user);
    }

    @PutMapping(value = "users")
    public Response<User> actionUpdateUser(@RequestBody User requestUser) {
        requestUser.setId(getCurrentUser().getId());
        User user = userService.update(requestUser);
        addCurrentUser(user);
        return new Response<>();
    }

    @PutMapping(value = "users/email")
    public Response<User> actionUpdateUserEmail(@RequestBody User requestUser) {
        requestUser.setId(getCurrentUser().getId());
        User user = userService.updateEmail(requestUser);
        addCurrentUser(user);
        return new Response<>();
    }

    @PutMapping(value = "users/password")
    public Response<User> actionUpdateUserPassword(@RequestBody UserReq requestUser) {
        UserPwd userPwd = requestUser.getPwdUser();
        userPwd.setId(getCurrentUser().getId());
        User user = userService.updatePassword(userPwd);
        addCurrentUser(user);
        return new Response<>();
    }

    @PutMapping(value = "users/avatar")
    public Response<User> actionUpdateUserAvatar(@RequestBody User requestUser) {
        requestUser.setId(getCurrentUser().getId());
        User user = userService.updateUserAvatar(requestUser);
        addCurrentUser(user);
        return new Response<>();
    }

    @PutMapping(value = "users/portrait")
    public Response<User> actionUpdateUserPortrait(@RequestBody User requestUser) {
        requestUser.setId(getCurrentUser().getId());
        User user = userService.updateUserPortrait(requestUser);
        addCurrentUser(user);
        return new Response<>();
    }

    @PutMapping(value = "users/authorize")
    public Response<User> actionUserAuthorization(@RequestBody User requestUser) {
        userService.authorizeUser(requestUser);
        return new Response<>();
    }

    @GetMapping(value = "users/{id}")
    public Response<User> actionQueryUserContainsBookList(@PathVariable("id") Integer id) {
        User user = userService.findUserContainsBookList(id);
        return new Response<>(user);
    }

    @GetMapping(value = "users/current")
    public Response<User> actionQueryCurrentUser() {
        User user = userService.findUserContainsBookList(getCurrentUser().getId());
        return new Response<>(user);
    }

    @GetMapping(value = "users/query/{code}")
    public Response<User> actionQueryUserByMobilePhoneNumberOrEmail(@PathVariable(value = "code") String code) {
        User user = userService.findUserByMobilePhoneNumber(code);
        return new Response<>(user);
    }
}
