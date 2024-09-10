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
import com.ruoyi.system.domain.MovieUser;
import com.ruoyi.system.service.IMovieUserService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户管理Controller
 * 
 * @author ruoyi
 * @date 2024-01-15
 */
@Controller
@RequestMapping("/system/MovieUser")
public class MovieUserController extends BaseController
{
    private String prefix = "system/MovieUser";

    @Autowired
    private IMovieUserService movieUserService;

    @RequiresPermissions("system:MovieUser:view")
    @GetMapping()
    public String MovieUser()
    {
        return prefix + "/MovieUser";
    }

    /**
     * 查询用户管理列表
     */
    @RequiresPermissions("system:MovieUser:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MovieUser movieUser)
    {
        startPage();
        List<MovieUser> list = movieUserService.selectMovieUserList(movieUser);
        return getDataTable(list);
    }

    /**
     * 导出用户管理列表
     */
    @RequiresPermissions("system:MovieUser:export")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MovieUser movieUser)
    {
        List<MovieUser> list = movieUserService.selectMovieUserList(movieUser);
        ExcelUtil<MovieUser> util = new ExcelUtil<MovieUser>(MovieUser.class);
        return util.exportExcel(list, "用户管理数据");
    }

    /**
     * 新增用户管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户管理
     */
    @RequiresPermissions("system:MovieUser:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MovieUser movieUser)
    {
        return toAjax(movieUserService.insertMovieUser(movieUser));
    }

    /**
     * 修改用户管理
     */
    @RequiresPermissions("system:MovieUser:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        MovieUser movieUser = movieUserService.selectMovieUserById(id);
        mmap.put("movieUser", movieUser);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户管理
     */
    @RequiresPermissions("system:MovieUser:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MovieUser movieUser)
    {
        return toAjax(movieUserService.updateMovieUser(movieUser));
    }

    /**
     * 删除用户管理
     */
    @RequiresPermissions("system:MovieUser:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(movieUserService.deleteMovieUserByIds(ids));
    }
}
