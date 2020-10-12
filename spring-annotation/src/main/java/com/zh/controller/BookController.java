package com.zh.controller;

import com.zh.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author Zhaohui
 * @date 2020/10/8
 */
// @Scope("prototype")
@Controller
public class BookController {
    @Autowired
    private BookService bookService;
}
