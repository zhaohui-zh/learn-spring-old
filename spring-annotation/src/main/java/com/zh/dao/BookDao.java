package com.zh.dao;

import org.springframework.stereotype.Repository;

/**
 * @author Zhaohui
 * @date 2020/10/8
 */
// 名字默认是类名首字母小写
@Repository
public class BookDao {

    private String label = "1";

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "BookDao{" +
                "label='" + label + '\'' +
                '}';
    }
}
