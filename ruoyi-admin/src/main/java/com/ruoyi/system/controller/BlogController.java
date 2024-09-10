package com.ruoyi.system.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Blog;
import com.ruoyi.system.service.IBlogService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 电影资讯管理Controller
 * 
 * @author ruoyi
 * @date 2024-01-15
 */
@Controller
@RequestMapping("/system/blog")
public class BlogController extends BaseController
{
    private String prefix = "system/blog";

    @Autowired
    private IBlogService blogService;

    @RequiresPermissions("system:blog:view")
    @GetMapping()
    public String blog()
    {
        return prefix + "/blog";
    }

    /**
     * 查询电影资讯管理列表
     */
    @RequiresPermissions("system:blog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Blog blog)
    {
        startPage();
        List<Blog> list = blogService.selectBlogList(blog);
        return getDataTable(list);
    }

    /**
     * 导出电影资讯管理列表
     */
    @RequiresPermissions("system:blog:export")
    @Log(title = "电影资讯管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Blog blog)
    {
        List<Blog> list = blogService.selectBlogList(blog);
        ExcelUtil<Blog> util = new ExcelUtil<Blog>(Blog.class);
        return util.exportExcel(list, "电影资讯管理数据");
    }

    /**
     * 新增电影资讯管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存电影资讯管理
     */
    @RequiresPermissions("system:blog:add")
    @Log(title = "电影资讯管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Blog blog)
    {
        return toAjax(blogService.insertBlog(blog));
    }

    /**
     * 修改电影资讯管理
     */
    @RequiresPermissions("system:blog:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Blog blog = blogService.selectBlogById(id);
        mmap.put("blog", blog);
        return prefix + "/edit";
    }

    /**
     * 修改保存电影资讯管理
     */
    @RequiresPermissions("system:blog:edit")
    @Log(title = "电影资讯管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Blog blog)
    {
        return toAjax(blogService.updateBlog(blog));
    }

    /**
     * 删除电影资讯管理
     */
    @RequiresPermissions("system:blog:remove")
    @Log(title = "电影资讯管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(blogService.deleteBlogByIds(ids));
    }
}
