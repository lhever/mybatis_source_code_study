package com.lhever.demo.mybatis.study;

import com.lhever.demo.mybatis.study.dao.BookDao;
import com.lhever.demo.mybatis.study.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2019/12/21 12:32
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019/12/21 12:32
 * @modify by reason:{方法名}:{原因}
 */
@RestController
@RequestMapping("book")
public class BookController {


    @Autowired
    private BookDao bookDao;


    @ResponseBody
    @GetMapping("all")
    public List<Book> getall() {
        return bookDao.getAll();
    }

    @ResponseBody
    @GetMapping("add")
    public Map<String, Object> add() {
        Book book = createBook("add");
        int i = bookDao.addBook(book);

        return new HashMap() {{
            put("update count: ", i);
            put("id", book.getId());
        }};


    }


    @ResponseBody
    @GetMapping("insert")
    public Map<String, Object> insert() {
        Book book = createBook("insert");

        int i = bookDao.insert(book);

        return new HashMap() {{
            put("update count: ", i);
            put("id", book.getId());
        }};


    }

    private Book createBook(String prefix) {
        Book book = new Book();
        book.setPrice(1.1f);
        book.setName(prefix + "-哲学-" + UUID.randomUUID().toString());
        book.setCreateTime(new Date());
        return book;
    }


    @ResponseBody
    @GetMapping("insertAll")
    public List<Book> insertAll() {
        List<Book> books = Arrays.asList(createBook("insertAll"), createBook("insertAll"));
        //id可以正确返回
        bookDao.insertInBatch(books);
        return books;
    }

    @ResponseBody
    @GetMapping("addAll")
    public List<Book> addAll() {
        List<Book> books = Arrays.asList(createBook("insertAll"), createBook("insertAll"));
        //id无法正确返回
        bookDao.addAll(books);

        return books;
    }


    @ResponseBody
    @GetMapping("getBy")
    public List<Book> getBy(@RequestParam("id") Integer id) {
        Book book = new Book();
        book.setId(id);
        return bookDao.getBy(book);

    }


}
