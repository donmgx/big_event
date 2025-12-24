package com.hebei.controller;

import com.hebei.pojo.Article;
import com.hebei.pojo.PageBean;
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
        log.info("新增文章：{}", article);
        articleSever.add(article);
        return Result.success();
    }

    /*
     * 文章分页查询
     * */
    @GetMapping
    public Result<PageBean<Article>> listPage(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ) {
        log.info("文章分页查询");
        PageBean<Article> pageBean = articleSever.listPage(pageNum, pageSize, categoryId, state);
        return Result.success(pageBean);
    }
}
