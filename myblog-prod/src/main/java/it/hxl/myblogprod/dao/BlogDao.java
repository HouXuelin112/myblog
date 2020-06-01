package it.hxl.myblogprod.dao;

import it.hxl.myblogprod.entity.Blog;

import java.util.List;

public interface BlogDao {
    /**
     * 获取非置顶的Blog
     * @return 返回Blog的list
     */
    List<Blog> findNotTopBlogsByTagId(int tagId);

    /**
     * 获取非置顶的Blog
     * @return 返回Blog的list
     */
    List<Blog> findNotTopBlogs();

    /**
     * 根据热度获取前几行
     * @param rows 为行数
     * @return 返回Blog的list
     */
    List<Blog> findHotTopBlogs(int rows);

    /**
     * 根据id获取Blog
     * @param id blog的id
     * @return 一个Blog实体
     */
    Blog findBlogById(int id);

    /**
     * 获取置顶的Blog并返回
     * @return Blog的list
     */
    List<Blog> findTopBlogs();

    /**
     * 获取置顶的Blog并返回
     * @return Blog的list
     */
    List<Blog> findTopBlogsByTagId(int tagId);

    /**
     * 模糊查询
     * @param text
     * @return
     */
    List<Blog> findBlogsLike(String text);

}
