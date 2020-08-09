package com.windypath.blog.common.model;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


/**
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/8 20:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageModel<T> implements Serializable {
    public PageModel(List<T> list, PageInfo pageInfo) {
        this.list = list;
        this.totalNum = pageInfo.getTotal();
    }

    /**
     * 当页数据
     */
    private List<T> list;

    /**
     * 数据总条数
     */
    private Long totalNum;
}
