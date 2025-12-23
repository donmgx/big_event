package com.hebei.server;

import com.hebei.pojo.Category;

public interface CategoryServer {
    /*
     * 新增文章分类
     * */
    void add(Category category) throws Exception;

}
