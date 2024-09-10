package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("key_value")
public class KeyValue {
    public Long id;
    public String keym;
    public Long valuem;

    @TableField(value = "create_time",fill= FieldFill.INSERT)
    public Date createTime;
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeym() {
        return keym;
    }

    public void setKeym(String keym) {
        this.keym = keym;
    }

    public Long getValuem() {
        return valuem;
    }

    public void setValuem(Long valuem) {
        this.valuem = valuem;
    }
}

