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
import com.ruoyi.system.domain.Comment;
import com.ruoyi.system.service.ICommentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 评论管理Controller
 * 
 * @author ruoyi
 * @date 2024-01-15
 */
@Controller
@RequestMapping("/system/comment")
public class CommentController extends BaseController
{
    private String prefix = "system/comment";

    @Autowired
    private ICommentService commentService;

    @RequiresPermissions("system:comment:view")
    @GetMapping()
    public String comment()
    {
        return prefix + "/comment";
    }

    /**
     * 查询评论管理列表
     */
    @RequiresPermissions("system:comment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Comment comment)
    {
        startPage();
        List<Comment> list = commentService.selectCommentList(comment);
        return getDataTable(list);
    }

    /**
     * 导出评论管理列表
     */
    @RequiresPermissions("system:comment:export")
    @Log(title = "评论管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Comment comment)
    {
        List<Comment> list = commentService.selectCommentList(comment);
        ExcelUtil<Comment> util = new ExcelUtil<Comment>(Comment.class);
        return util.exportExcel(list, "评论管理数据");
    }

    /**
     * 新增评论管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存评论管理
     */
    @RequiresPermissions("system:comment:add")
    @Log(title = "评论管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Comment comment)
    {
        return toAjax(commentService.insertComment(comment));
    }

    /**
     * 修改评论管理
     */
    @RequiresPermissions("system:comment:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Comment comment = commentService.selectCommentById(id);
        mmap.put("comment", comment);
        return prefix + "/edit";
    }

    /**
     * 修改保存评论管理
     */
    @RequiresPermissions("system:comment:edit")
    @Log(title = "评论管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Comment comment)
    {
        return toAjax(commentService.updateComment(comment));
    }

    /**
     * 删除评论管理
     */
    @RequiresPermissions("system:comment:remove")
    @Log(title = "评论管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(commentService.deleteCommentByIds(ids));
    }
}
