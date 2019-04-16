package cn.edu.qfnu.meta.controller;

import cn.edu.qfnu.meta.model.domain.Page;
import cn.edu.qfnu.meta.model.dto.Response;
import cn.edu.qfnu.meta.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 漫画分页业务API
 *
 * @author 王振琦
 * createAt: 2018/08/01
 * updateAt: 2018/10/10
 */
@RestController
@RequestMapping(value = "api")
public class PageController extends MetaFacade {

    private final PageService pageService;

    @Autowired
    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @PostMapping(value = "lessons")
    public Response<Page> actionSavePage(@RequestBody Page page) {
        pageService.save(page);
        return new Response<>(page);
    }

    @DeleteMapping(value = "lessons/{id}")
    public Response<Page> actionDeletePage(@PathVariable(value = "id") Integer id) {
        pageService.delete(id);
        return new Response<>();
    }

    @PutMapping(value = "lessons")
    public Response<Page> actionUpdatePage(@RequestBody Page page) {
        pageService.update(page);
        return new Response<>();
    }

    @GetMapping(value = "pages/{id}")
    public Response<Page> actionQueryPage(@PathVariable(value = "id") Integer id) {
        Page page = pageService.findPageContainsBook(id);
        return new Response<>(page);
    }

    @GetMapping(value = "books/{bookId}/pages")
    public Response<List<Page>> actionQueryPageByBook(@PathVariable(value = "bookId") Integer bookId) {
        List<Page> pageList = pageService.findPageListByBook(bookId);
        return new Response<>(pageList);
    }
}
