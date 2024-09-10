package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 评论管理对象 comment
 * 
 * @author ruoyi
 * @date 2024-01-15
 */
public class Comment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 评论 */
    @Excel(name = "评论")
    private String comment;

    /** 评分 */
    @Excel(name = "评分")
    private Long score;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 电影id */
    @Excel(name = "电影id")
    private Long movieId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setComment(String comment) 
    {
        this.comment = comment;
    }

    public String getComment() 
    {
        return comment;
    }
    public void setScore(Long score) 
    {
        this.score = score;
    }

    public Long getScore() 
    {
        return score;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setMovieId(Long movieId) 
    {
        this.movieId = movieId;
    }

    public Long getMovieId() 
    {
        return movieId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("comment", getComment())
            .append("score", getScore())
            .append("userId", getUserId())
            .append("movieId", getMovieId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
