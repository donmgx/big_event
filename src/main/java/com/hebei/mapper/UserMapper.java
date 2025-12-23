package com.hebei.mapper;

import com.hebei.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    /*
    * 根据username查询用户信息
    * */
    @Select("select * from user where username = #{username}")
    User findByUsername(String username);


    /*
    * 插入新用户
    * */
    @Insert("insert into user(username, password, create_time, update_time) VALUES (#{username},#{password},#{createTime},#{updateTime})")
    void insert(User user);

    /*
     * 更新用户信息
     * */
    @Update("update user set nickname = #{nickname},email = #{email} where id = #{id}")
    void update(User user);
}
