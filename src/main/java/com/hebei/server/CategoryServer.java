package com.hebei.server;

import com.hebei.pojo.Category;

import java.util.List;

public interface CategoryServer {
    /*
     * 新增文章分类
     * */
    void add(Category category) throws Exception;


    /*
     * 获取文章分类
     * */
    List<Category> list();


    /*
     * 获取文章分类详情
     * */
    Category findCategory(Integer id);


    /*
     * 更新文章分类
     * */
    void update(Category category);


    /*
     * 删除文章分类
     * */
    void deleteCategory(Integer id) throws Exception;
}
