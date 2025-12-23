package com.hebei.server.impl;

import com.hebei.constant.CommenConstant;
import com.hebei.mapper.CategoryMapper;
import com.hebei.pojo.Category;
import com.hebei.server.CategoryServer;
import com.hebei.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
