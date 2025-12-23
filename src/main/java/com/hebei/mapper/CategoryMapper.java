package com.hebei.mapper;

import com.hebei.annotation.AutoFill;
import com.hebei.enumeration.OperationType;
import com.hebei.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryMapper {

    /*
    * 根据名称查找分类
    * */
    @Select("select * from category where category_name = #{categoryName}")
    Category find(Category category);


    /*
    * 新增分类
    * */
    @AutoFill(value = OperationType.INSERT)
    @Insert("insert into category (category_name, category_alias, create_user, create_time, update_time) VALUES (#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateName})")
    void insert(Category category);
}
