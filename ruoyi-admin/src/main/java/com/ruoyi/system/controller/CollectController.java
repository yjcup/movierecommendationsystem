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
import com.ruoyi.system.domain.Collect;
import com.ruoyi.system.service.ICollectService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 电影收藏管理Controller
 * 
 * @author ruoyi
 * @date 2024-01-16
 */
@Controller
@RequestMapping("/system/collect")
public class CollectController extends BaseController
{
    private String prefix = "system/collect";

    @Autowired
    private ICollectService collectService;

    @RequiresPermissions("system:collect:view")
    @GetMapping()
    public String collect()
    {
        return prefix + "/collect";
    }

    /**
     * 查询电影收藏管理列表
     */
    @RequiresPermissions("system:collect:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Collect collect)
    {
        startPage();
        List<Collect> list = collectService.selectCollectList(collect);
        return getDataTable(list);
    }

    /**
     * 导出电影收藏管理列表
     */
    @RequiresPermissions("system:collect:export")
    @Log(title = "电影收藏管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Collect collect)
    {
        List<Collect> list = collectService.selectCollectList(collect);
        ExcelUtil<Collect> util = new ExcelUtil<Collect>(Collect.class);
        return util.exportExcel(list, "电影收藏管理数据");
    }

    /**
     * 新增电影收藏管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存电影收藏管理
     */
    @RequiresPermissions("system:collect:add")
    @Log(title = "电影收藏管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Collect collect)
    {
        return toAjax(collectService.insertCollect(collect));
    }

    /**
     * 修改电影收藏管理
     */
    @RequiresPermissions("system:collect:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Collect collect = collectService.selectCollectById(id);
        mmap.put("collect", collect);
        return prefix + "/edit";
    }

    /**
     * 修改保存电影收藏管理
     */
    @RequiresPermissions("system:collect:edit")
    @Log(title = "电影收藏管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Collect collect)
    {
        return toAjax(collectService.updateCollect(collect));
    }

    /**
     * 删除电影收藏管理
     */
    @RequiresPermissions("system:collect:remove")
    @Log(title = "电影收藏管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(collectService.deleteCollectByIds(ids));
    }
}
