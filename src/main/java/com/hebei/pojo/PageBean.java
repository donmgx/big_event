package com.hebei.pojo;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//分页返回结果对象
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean <T>{
    @NotEmpty
    private Long total;//总条数
    @NotEmpty
    private List<T> items;//当前页数据集合
}
