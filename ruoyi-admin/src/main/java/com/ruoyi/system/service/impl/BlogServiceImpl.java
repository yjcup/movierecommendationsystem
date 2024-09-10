package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BlogMapper;
import com.ruoyi.system.domain.Blog;
import com.ruoyi.system.service.IBlogService;
import com.ruoyi.common.core.text.Convert;

/**
 * 电影资讯管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-15
 */
@Service
public class BlogServiceImpl implements IBlogService 
{
    @Autowired
    private BlogMapper blogMapper;

    /**
     * 查询电影资讯管理
     * 
     * @param id 电影资讯管理主键
     * @return 电影资讯管理
     */
    @Override
    public Blog selectBlogById(Long id)
    {
        return blogMapper.selectBlogById(id);
    }

    /**
     * 查询电影资讯管理列表
     * 
     * @param blog 电影资讯管理
     * @return 电影资讯管理
     */
    @Override
    public List<Blog> selectBlogList(Blog blog)
    {
        return blogMapper.selectBlogList(blog);
    }

    /**
     * 新增电影资讯管理
     * 
     * @param blog 电影资讯管理
     * @return 结果
     */
    @Override
    public int insertBlog(Blog blog)
    {
        blog.setCreateTime(DateUtils.getNowDate());
        return blogMapper.insertBlog(blog);
    }

    /**
     * 修改电影资讯管理
     * 
     * @param blog 电影资讯管理
     * @return 结果
     */
    @Override
    public int updateBlog(Blog blog)
    {
        return blogMapper.updateBlog(blog);
    }

    /**
     * 批量删除电影资讯管理
     * 
     * @param ids 需要删除的电影资讯管理主键
     * @return 结果
     */
    @Override
    public int deleteBlogByIds(String ids)
    {
        return blogMapper.deleteBlogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除电影资讯管理信息
     * 
     * @param id 电影资讯管理主键
     * @return 结果
     */
    @Override
    public int deleteBlogById(Long id)
    {
        return blogMapper.deleteBlogById(id);
    }
}
