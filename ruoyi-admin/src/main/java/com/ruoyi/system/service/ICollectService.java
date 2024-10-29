package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Collect;

/**
 * 电影收藏管理Service接口
 * 
 * @author ruoyi
 * @date 2024-01-16
 */
public interface ICollectService 
{
    /**
     * 查询电影收藏管理
     * 
     * @param id 电影收藏管理主键
     * @return 电影收藏管理
     */
    public Collect selectCollectById(Long id);

    /**
     * 查询电影收藏管理列表
     * 
     * @param collect 电影收藏管理
     * @return 电影收藏管理集合
     */
    public List<Collect> selectCollectList(Collect collect);

    /**
     * 新增电影收藏管理
     * 
     * @param collect 电影收藏管理
     * @return 结果
     */
    public int insertCollect(Collect collect);

    /**
     * 修改电影收藏管理
     * 
     * @param collect 电影收藏管理
     * @return 结果
     */
    public int updateCollect(Collect collect);

    /**
     * 批量删除电影收藏管理
     * 
     * @param ids 需要删除的电影收藏管理主键集合
     * @return 结果
     */
    public int deleteCollectByIds(String ids);

    /**
     * 删除电影收藏管理信息
     * 
     * @param id 电影收藏管理主键
     * @return 结果
     */
    public int deleteCollectById(Long id);
}