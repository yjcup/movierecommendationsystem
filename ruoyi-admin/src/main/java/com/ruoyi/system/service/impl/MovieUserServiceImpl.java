package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MovieUserMapper;
import com.ruoyi.system.domain.MovieUser;
import com.ruoyi.system.service.IMovieUserService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-15
 */
@Service
public class MovieUserServiceImpl implements IMovieUserService 
{
    @Autowired
    private MovieUserMapper movieUserMapper;

    /**
     * 查询用户管理
     * 
     * @param id 用户管理主键
     * @return 用户管理
     */
    @Override
    public MovieUser selectMovieUserById(Long id)
    {
        return movieUserMapper.selectMovieUserById(id);
    }

    /**
     * 查询用户管理列表
     * 
     * @param movieUser 用户管理
     * @return 用户管理
     */
    @Override
    public List<MovieUser> selectMovieUserList(MovieUser movieUser)
    {
        return movieUserMapper.selectMovieUserList(movieUser);
    }

    /**
     * 新增用户管理
     * 
     * @param movieUser 用户管理
     * @return 结果
     */
    @Override
    public int insertMovieUser(MovieUser movieUser)
    {
        movieUser.setCreateTime(DateUtils.getNowDate());
        return movieUserMapper.insertMovieUser(movieUser);
    }

    /**
     * 修改用户管理
     * 
     * @param movieUser 用户管理
     * @return 结果
     */
    @Override
    public int updateMovieUser(MovieUser movieUser)
    {
        return movieUserMapper.updateMovieUser(movieUser);
    }

    /**
     * 批量删除用户管理
     * 
     * @param ids 需要删除的用户管理主键
     * @return 结果
     */
    @Override
    public int deleteMovieUserByIds(String ids)
    {
        return movieUserMapper.deleteMovieUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户管理信息
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    @Override
    public int deleteMovieUserById(Long id)
    {
        return movieUserMapper.deleteMovieUserById(id);
    }
}
