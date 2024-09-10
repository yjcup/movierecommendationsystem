package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.MovieUser;

/**
 * 用户管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-15
 */
public interface MovieUserMapper 
{
    /**
     * 查询用户管理
     * 
     * @param id 用户管理主键
     * @return 用户管理
     */
    public MovieUser selectMovieUserById(Long id);

    /**
     * 查询用户管理列表
     * 
     * @param movieUser 用户管理
     * @return 用户管理集合
     */
    public List<MovieUser> selectMovieUserList(MovieUser movieUser);

    /**
     * 新增用户管理
     * 
     * @param movieUser 用户管理
     * @return 结果
     */
    public int insertMovieUser(MovieUser movieUser);

    /**
     * 修改用户管理
     * 
     * @param movieUser 用户管理
     * @return 结果
     */
    public int updateMovieUser(MovieUser movieUser);

    /**
     * 删除用户管理
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    public int deleteMovieUserById(Long id);

    /**
     * 批量删除用户管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMovieUserByIds(String[] ids);
}
