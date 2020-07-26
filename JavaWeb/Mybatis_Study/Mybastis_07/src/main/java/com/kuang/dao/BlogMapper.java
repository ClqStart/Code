package com.kuang.dao;

import com.kuang.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {
    int addBlog(Blog blog);

    //查询
    List<Blog> queryBlogIf(Map map);
    List<Blog> queryBlogChoose(Map map);
    List<Blog> queryBlogForeach(Map map);

    int updateBlog(Map map);
}
