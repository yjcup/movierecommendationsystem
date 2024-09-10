package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CollectMapper;
import com.ruoyi.system.domain.Collect;
import com.ruoyi.system.service.ICollectService;
import com.ruoyi.common.core.text.Convert;

/**
 * 电影收藏管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-16
 */
@Service
public class CollectServiceImpl implements ICollectService 
{
    @Autowired
    private CollectMapper collectMapper;

    /**
     * 查询电影收藏管理
     * 
     * @param id 电影收藏管理主键
     * @return 电影收藏管理
     */
    @Override
    public Collect selectCollectById(Long id)
    {
        return collectMapper.selectCollectById(id);
    }

    /**
     * 查询电影收藏管理列表
     * 
     * @param collect 电影收藏管理
     * @return 电影收藏管理
     */
    @Override
    public List<Collect> selectCollectList(Collect collect)
    {
        return collectMapper.selectCollectList(collect);
    }

    /**
     * 新增电影收藏管理
     * 
     * @param collect 电影收藏管理
     * @return 结果
     */
    @Override
    public int insertCollect(Collect collect)
    {
        collect.setCreateTime(DateUtils.getNowDate());
        return collectMapper.insertCollect(collect);
    }

    /**
     * 修改电影收藏管理
     * 
     * @param collect 电影收藏管理
     * @return 结果
     */
    @Override
    public int updateCollect(Collect collect)
    {
        return collectMapper.updateCollect(collect);
    }

    /**
     * 批量删除电影收藏管理
     * 
     * @param ids 需要删除的电影收藏管理主键
     * @return 结果
     */
    @Override
    public int deleteCollectByIds(String ids)
    {
        return collectMapper.deleteCollectByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除电影收藏管理信息
     * 
     * @param id 电影收藏管理主键
     * @return 结果
     */
    @Override
    public int deleteCollectById(Long id)
    {
        return collectMapper.deleteCollectById(id);
    }
}
