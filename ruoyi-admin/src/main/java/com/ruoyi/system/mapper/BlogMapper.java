package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Blog;

/**
 * 电影资讯管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-15
 */
public interface BlogMapper 
{
    /**
     * 查询电影资讯管理
     * 
     * @param id 电影资讯管理主键
     * @return 电影资讯管理
     */
    public Blog selectBlogById(Long id);

    /**
     * 查询电影资讯管理列表
     * 
     * @param blog 电影资讯管理
     * @return 电影资讯管理集合
     */
    public List<Blog> selectBlogList(Blog blog);

    /**
     * 新增电影资讯管理
     * 
     * @param blog 电影资讯管理
     * @return 结果
     */
    public int insertBlog(Blog blog);

    /**
     * 修改电影资讯管理
     * 
     * @param blog 电影资讯管理
     * @return 结果
     */
    public int updateBlog(Blog blog);

    /**
     * 删除电影资讯管理
     * 
     * @param id 电影资讯管理主键
     * @return 结果
     */
    public int deleteBlogById(Long id);

    /**
     * 批量删除电影资讯管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlogByIds(String[] ids);
}
