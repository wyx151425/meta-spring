package cn.edu.qfnu.meta.controller;

import cn.edu.qfnu.meta.model.domain.Book;
import cn.edu.qfnu.meta.model.dto.Response;
import cn.edu.qfnu.meta.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 漫画册业务API
 *
 * @author 王振琦
 * createAt: 2018/08/01
 * updateAt: 2018/11/06
 */
@RestController
@RequestMapping(value = "api")
public class BookController extends MetaFacade {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "books")
    public Response<Book> actionSaveBook(@RequestBody Book book) {
        book.setAuthor(getCurrentUser());
        bookService.saveBook(book);
        return new Response<>(book);
    }

    @PutMapping(value = "books")
    public Response<Book> actionUpdateBook(@RequestBody Book book) {
        bookService.updateBook(book);
        return new Response<>();
    }

    @PutMapping(value = "books/{id}/publish")
    public Response<Book> actionPublishBook(@PathVariable(value = "id") Integer id) {
        bookService.publishBook(id);
        return new Response<>();
    }

    @DeleteMapping(value = "books/{id}")
    public Response<Book> actionDeleteBook(@PathVariable(value = "id") Integer id) {
        bookService.deleteBook(id);
        return new Response<>();
    }

    @GetMapping(value = "books/{id}")
    public Response<Book> actionQueryBookContainsAuthor(@PathVariable(value = "id") Integer id) {
        Book book = bookService.findBookContainsAuthorAndPageList(id);
        return new Response<>(book);
    }

    @GetMapping(value = "books")
    public Response<List<Book>> actionQueryBookList() {
        List<Book> bookList = bookService.findBookList();
        return new Response<>(bookList);
    }

    @GetMapping(value = "courses/rank")
    public Response<List<Book>> actionQueryBookListByRank(@RequestParam(value = "index") Integer index) {
        List<Book> bookList = bookService.findBookListByRank(index);
        return new Response<>(bookList);
    }

    @GetMapping(value = "courses")
    public Response<List<Book>> actionQueryCourseListByStyleAndType(
            @RequestParam(value = "style") String style,
            @RequestParam(value = "type") String type
    ) {
        List<Book> bookList = bookService.findBookListByStyleAndType(style, type);
        return new Response<>(bookList);
    }

    @GetMapping(value = "courses/name")
    public Response<List<Book>> actionQueryCoursesByName(
            @RequestParam(value = "name") String name
    ) {
        List<Book> bookList = bookService.findCoursesByName(name);
        return new Response<>(bookList);
    }
}
