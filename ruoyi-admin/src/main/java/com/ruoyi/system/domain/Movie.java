package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 电影信息管理对象 movie
 *
 * @author ruoyi
 * @date 2024-01-17
 */
public class Movie extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 简介 */
    @Excel(name = "简介")
    private String info;

    /** 电影名 */
    @Excel(name = "电影名")
    private String name;

    /** 封面 */
    @Excel(name = "封面")
    private String img;

    /** 导演 */
    @Excel(name = "导演")
    private String direct;

    /** 演员 */
    @Excel(name = "演员")
    private String starts;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publicTime;

    /** 时长 */
    @Excel(name = "时长")
    private String runtime;

    /** 剧照 */
    @Excel(name = "剧照")
    private String photos;

    /** 分类 */
    @Excel(name = "分类")
    private String type;

    /** 评分 */
    @Excel(name = "评分")
    private Long rate;

    /** 点击数 */
    @Excel(name = "点击数")
    private Long count;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setInfo(String info)
    {
        this.info = info;
    }

    public String getInfo()
    {
        return info;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setImg(String img)
    {
        this.img = img;
    }

    public String getImg()
    {
        return img;
    }
    public void setDirect(String direct)
    {
        this.direct = direct;
    }

    public String getDirect()
    {
        return direct;
    }
    public void setStarts(String starts)
    {
        this.starts = starts;
    }

    public String getStarts()
    {
        return starts;
    }
    public void setPublicTime(Date publicTime)
    {
        this.publicTime = publicTime;
    }

    public Date getPublicTime()
    {
        return publicTime;
    }
    public void setRuntime(String runtime)
    {
        this.runtime = runtime;
    }

    public String getRuntime()
    {
        return runtime;
    }
    public void setPhotos(String photos)
    {
        this.photos = photos;
    }

    public String getPhotos()
    {
        return photos;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setRate(Long rate)
    {
        this.rate = rate;
    }

    public Long getRate()
    {
        return rate;
    }
    public void setCount(Long count)
    {
        this.count = count;
    }

    public Long getCount()
    {
        return count;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("info", getInfo())
                .append("name", getName())
                .append("img", getImg())
                .append("direct", getDirect())
                .append("starts", getStarts())
                .append("publicTime", getPublicTime())
                .append("runtime", getRuntime())
                .append("photos", getPhotos())
                .append("createTime", getCreateTime())
                .append("type", getType())
                .append("rate", getRate())
                .append("count", getCount())
                .toString();
    }
}
