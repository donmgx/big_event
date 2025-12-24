package com.hebei.server.impl;

import com.hebei.constant.CommenConstant;
import com.hebei.mapper.CategoryMapper;
import com.hebei.pojo.Category;
import com.hebei.server.CategoryServer;
import com.hebei.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class CategoryServerImpl implements CategoryServer {
    @Autowired
    private CategoryMapper categoryMapper;


    /*
     * 新增文章分类
     * */
    public void add(Category category) throws Exception {
        //判断分类是否重复
        Category existingCategory = categoryMapper.find(category);
        if (existingCategory != null) {
            throw new Exception(CommenConstant.CATEGORY_HAS_EXIST);
        }

        //如果不存在
        Map<String, Object> map = ThreadLocalUtil.get();
        if (map == null) {
            throw new Exception(CommenConstant.USER_INFO_NOT_EXIST);
        }
        Integer id = (Integer) map.get("id");
        if (id == null) {
            throw new Exception(CommenConstant.USERID_NOT_EXIST);
        }
        category.setCreateUser(id);
        categoryMapper.insert(category);
    }


    /*
     * 获取文章分类
     * */
    public List<Category> list() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer createUser = (Integer) map.get("id");
        List<Category> categories = categoryMapper.findByCreateUser(createUser);
        return categories;
    }


    /*
     * 获取文章分类详情
     * */
    public Category findCategory(Integer id) {
        Category category = categoryMapper.findById(id);
        return category;
    }


    /*
     * 更新文章分类
     * */
    public void update(Category category) {
        categoryMapper.update(category);
    }


    /*
     * 删除文章分类
     * */
    public void deleteCategory(Integer id) throws Exception {
        //判断该分类是否存在
        Category userById = categoryMapper.findById(id);
        if (userById == null){
            throw new Exception(CommenConstant.CATEGORY_NOT_EXIST);
        }
        categoryMapper.delete(id);

    }


}
