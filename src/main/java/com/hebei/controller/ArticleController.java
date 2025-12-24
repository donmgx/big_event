package com.hebei.controller;

import com.hebei.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/article")
public class ArticleController {

    /*
    * 新增文章
    * */
    @PostMapping
    public Result add(){
        log.info("新增文章：{}");
        return Result.success();
    }

    /*
    *
    * */
    @GetMapping("/list")
    public Result list(){

        return Result.success("所有文章数据");
    }
}
