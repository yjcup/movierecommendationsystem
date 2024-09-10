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
import com.ruoyi.system.domain.Movie;
import com.ruoyi.system.service.IMovieService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 电影信息管理Controller
 * 
 * @author ruoyi
 * @date 2024-01-15
 */
@Controller
@RequestMapping("/system/movie")
public class MovieController extends BaseController
{
    private String prefix = "system/movie";

    @Autowired
    private IMovieService movieService;

    @RequiresPermissions("system:movie:view")
    @GetMapping()
    public String movie()
    {
        return prefix + "/movie";
    }

    /**
     * 查询电影信息管理列表
     */
    @RequiresPermissions("system:movie:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Movie movie)
    {
        startPage();
        List<Movie> list = movieService.selectMovieList(movie);
        return getDataTable(list);
    }

    /**
     * 导出电影信息管理列表
     */
    @RequiresPermissions("system:movie:export")
    @Log(title = "电影信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Movie movie)
    {
        List<Movie> list = movieService.selectMovieList(movie);
        ExcelUtil<Movie> util = new ExcelUtil<Movie>(Movie.class);
        return util.exportExcel(list, "电影信息管理数据");
    }

    /**
     * 新增电影信息管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存电影信息管理
     */
    @RequiresPermissions("system:movie:add")
    @Log(title = "电影信息管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Movie movie)
    {
        return toAjax(movieService.insertMovie(movie));
    }

    /**
     * 修改电影信息管理
     */
    @RequiresPermissions("system:movie:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Movie movie = movieService.selectMovieById(id);
        mmap.put("movie", movie);
        return prefix + "/edit";
    }

    /**
     * 修改保存电影信息管理
     */
    @RequiresPermissions("system:movie:edit")
    @Log(title = "电影信息管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Movie movie)
    {
        return toAjax(movieService.updateMovie(movie));
    }

    /**
     * 删除电影信息管理
     */
    @RequiresPermissions("system:movie:remove")
    @Log(title = "电影信息管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(movieService.deleteMovieByIds(ids));
    }
}
