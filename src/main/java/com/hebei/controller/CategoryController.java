package com.hebei.controller;

import com.hebei.pojo.Category;
import com.hebei.pojo.Result;
import com.hebei.server.CategoryServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryServer categoryServer;
    /*
    * 新增文章分类
    * */
    @PostMapping
    public Result add(@RequestBody @Validated Category category) throws Exception {
        log.info("新增文章分类：{}",category);

        categoryServer.add(category);

        return Result.success();

    }
}
