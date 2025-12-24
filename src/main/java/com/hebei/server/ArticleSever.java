package com.hebei.server;

import com.hebei.pojo.Article;
import com.hebei.pojo.PageBean;

public interface ArticleSever {

    /*
     * 新增文章
     * */
    void add(Article article);


    /*
     * 文章分页查询
     * */
    PageBean<Article> listPage(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
