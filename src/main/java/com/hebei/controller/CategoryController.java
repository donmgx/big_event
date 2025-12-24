package com.hebei.controller;

import com.hebei.pojo.Category;
import com.hebei.pojo.Result;
import com.hebei.server.CategoryServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        log.info("新增文章分类：{}", category);

        categoryServer.add(category);

        return Result.success();

    }


    /*
     * 获取文章分类
     * */
    @GetMapping
    public Result<List<Category>> list() {
        log.info("获取文章分类");
        List<Category> categoryList = categoryServer.list();
        return Result.success(categoryList);
    }


    /*
     * 获取文章分类详情
     * */
    @GetMapping("/detail")
    public Result<Category> findCategory(@Validated Integer id) {
        log.info("获取文章分类详情：id：{}", id);
        Category category = categoryServer.findCategory(id);
        return Result.success(category);
    }

    /*
     * 更新文章分类
     * */
    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category) {
        log.info("更新文章分类");
        categoryServer.update(category);
        return Result.success();
    }

    /*
     * 删除文章分类
     * */
    @DeleteMapping
    public Result deleteCategory(@Validated(Category.Update.class) Integer id) throws Exception {
        log.info("删除文章分类：id：{}", id);
        categoryServer.deleteCategory(id);
        return Result.success();
    }
}
