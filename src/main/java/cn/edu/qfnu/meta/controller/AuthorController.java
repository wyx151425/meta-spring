package cn.edu.qfnu.meta.controller;

import cn.edu.qfnu.meta.model.domain.User;
import cn.edu.qfnu.meta.model.dto.Response;
import cn.edu.qfnu.meta.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 漫画册作者业务API
 *
 * @author 王振琦
 * createAt: 2018/11/06
 * updateAt: 2018/11/06
 */
@RestController
@RequestMapping(value = "api")
public class AuthorController extends MetaFacade {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "authors/{id}")
    public Response<User> actionQueryAuthorContainsBookList(@PathVariable(value = "id", required = false) Integer id) {
        User user = authorService.findAuthorContainsBookList(id);
        return new Response<>(user);
    }

    @GetMapping(value = "authors")
    public Response<List<User>> actionQueryAuthorList() {
        List<User> authorList = authorService.findAuthorList();
        return new Response<>(authorList);
    }

    @GetMapping(value = "authors/index")
    public Response<List<User>> actionQueryAuthorListForIndexPage() {
        List<User> authorList = authorService.findAuthorListForIndex();
        return new Response<>(authorList);
    }

    @GetMapping(value = "lecturers/rank")
    public Response<List<User>> actionQueryLecturerListByRank(@RequestParam(value = "index") Integer index) {
        List<User> userList = authorService.findLecturerListByRank(index);
        return new Response<>(userList);
    }
}
