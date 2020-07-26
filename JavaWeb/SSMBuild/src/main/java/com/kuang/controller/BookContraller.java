package com.kuang.controller;
/*
 *@author:C1q
 */

import com.kuang.pojo.Books;
import com.kuang.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookContraller {

    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    @RequestMapping("/allBook")
    public String list(Model model) {
        List<Books> list = bookService.queryAllBook();
        model.addAttribute("list", list);
        return "allBook";
    }

    /**
     * 跳转到添加书籍的页面
     *
     * @return
     */
    @RequestMapping("/toAddBook")
    public String toAddPaper() {

        return "addBook";
    }

    /**
     * 添加书籍
     *
     * @param books
     * @return
     */
    @RequestMapping("/addBook")
    public String addBook(Books books) {
        System.out.println("addBook=>" + books);

        bookService.addBook(books);
        return "redirect:/book/allBook"; //重定向
    }

    /**
     * 查询书籍
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toUpdate/{bookID}")
    public String toUpdatePaper(@PathVariable("bookID") int id, Model model) {
        Books books = bookService.queryBookById(id);
        model.addAttribute("Qbooks", books);
        return "updateBook";
    }

    /**
     * 修改书籍
     *
     * @param books
     * @return
     */
    @RequestMapping("/updateBook")
    public String updateBook(Books books) {
        System.out.println("updateBook");
        int i = bookService.updateBook(books);
        if (i > 0) {
            System.out.println("添加书籍成功" + books);
        }

        return "redirect:/book/allBook";
    }

    /**
     * 删除书籍
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteBook/{bookID}")
    public String deleteBook(@PathVariable("bookID") int id) {
        int i = bookService.deleteBookById(id);
        if (i > 0) {
            System.out.println("删除书籍成功");
        }
        return "redirect:/book/allBook";
    }

    @RequestMapping("/queryBookByName")
    public String queryBook(String queryBookName, Model model) {
        Books books = bookService.queryBookByName(queryBookName);
        List<Books> list = new ArrayList<Books>();
        if (books == null) {
            list = bookService.queryAllBook();
            model.addAttribute("error", "未查到");
        }else {
            list.add(books);
        }
        model.addAttribute("list",list);
        return "allBook";
    }


}
