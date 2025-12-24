package com.hebei.controller;

import com.hebei.pojo.Article;
import com.hebei.pojo.Result;
import com.hebei.server.ArticleSever;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleSever articleSever;

    /*
     * 新增文章
     * */
    @PostMapping
    public Result add(@RequestBody @Validated Article article) {
        log.info("新增文章：{}");
        articleSever.add(article);
        return Result.success();
    }

    /*
     * 文章分页查询
     * */
    @GetMapping
    public Result listPage() {

        return Result.success("所有文章数据");
    }
}
