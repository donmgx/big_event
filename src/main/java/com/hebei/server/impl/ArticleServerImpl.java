package com.hebei.server.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hebei.mapper.ArticleMapper;
import com.hebei.mapper.CategoryMapper;
import com.hebei.pojo.Article;
import com.hebei.pojo.Category;
import com.hebei.pojo.PageBean;
import com.hebei.server.ArticleSever;
import com.hebei.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServerImpl implements ArticleSever {

    @Autowired
    public CategoryMapper categoryMapper;
    @Autowired
    public ArticleMapper articleMapper;

    /*
     * 新增文章
     * */
    public void add(Article article) {
        //获取新增文章的用户
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);

        articleMapper.insert(article);

    }


    /*
     * 文章分页查询
     * */
    public PageBean<Article> listPage(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        PageHelper.startPage(pageNum, pageSize);
        Map<String,Object> map = ThreadLocalUtil.get();

        Integer createUser = (Integer) map.get("id");
        Page<Article> page = articleMapper.selectPage(createUser,categoryId,state);

        return new PageBean<>(page.getTotal(),page.getResult());
    }
}
