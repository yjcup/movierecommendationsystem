package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 电影收藏管理对象 collect
 * 
 * @author ruoyi
 * @date 2024-01-16
 */
public class Collect extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 电影ID */
    @Excel(name = "电影ID")
    private Long movieId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMovieId(Long movieId) 
    {
        this.movieId = movieId;
    }

    public Long getMovieId() 
    {
        return movieId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("movieId", getMovieId())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
