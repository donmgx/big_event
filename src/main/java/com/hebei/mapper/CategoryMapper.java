package com.hebei.mapper;

import com.hebei.annotation.AutoFill;
import com.hebei.enumeration.OperationType;
import com.hebei.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

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


    /*
     * 根据分类创建人获取文章分类
     * */
    @Select("select * from category where create_user = #{createUser}")
    List<Category> findByCreateUser(Integer createUser);


    /*
     * 根据分类id获取文章分类详情
     * */
    @Select("select * from category where id  = #{id}")
    Category findById(Integer id);


    /*
     * 删除文章分类
     * */
    @Delete("delete from category where id = #{id}")
    void delete(Integer id);

    /*
    * 更新分类
    * */
    @Update("update category set category_name = #{categoryName},category_alias = #{categoryAlias} where id = #{id}")
    void update(Category category);
}
