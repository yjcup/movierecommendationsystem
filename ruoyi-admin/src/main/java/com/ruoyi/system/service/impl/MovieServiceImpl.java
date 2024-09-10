package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MovieMapper;
import com.ruoyi.system.domain.Movie;
import com.ruoyi.system.service.IMovieService;
import com.ruoyi.common.core.text.Convert;

/**
 * 电影信息管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-15
 */
@Service
public class MovieServiceImpl implements IMovieService 
{
    @Autowired
    private MovieMapper movieMapper;

    public List<Movie> selectMovieRand(){
       return  movieMapper.selectMovieRand();
    }



    /**
     * 查询电影信息管理
     * 
     * @param id 电影信息管理主键
     * @return 电影信息管理
     */
    @Override
    public Movie selectMovieById(Long id)
    {
        return movieMapper.selectMovieById(id);
    }

    /**
     * 查询电影信息管理列表
     * 
     * @param movie 电影信息管理
     * @return 电影信息管理
     */
    @Override
    public List<Movie> selectMovieList(Movie movie)
    {
        return movieMapper.selectMovieList(movie);
    }

    /**
     * 新增电影信息管理
     * 
     * @param movie 电影信息管理
     * @return 结果
     */
    @Override
    public int insertMovie(Movie movie)
    {
        movie.setCreateTime(DateUtils.getNowDate());
        return movieMapper.insertMovie(movie);
    }

    /**
     * 修改电影信息管理
     * 
     * @param movie 电影信息管理
     * @return 结果
     */
    @Override
    public int updateMovie(Movie movie)
    {
        return movieMapper.updateMovie(movie);
    }

    /**
     * 批量删除电影信息管理
     * 
     * @param ids 需要删除的电影信息管理主键
     * @return 结果
     */
    @Override
    public int deleteMovieByIds(String ids)
    {
        return movieMapper.deleteMovieByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除电影信息管理信息
     * 
     * @param id 电影信息管理主键
     * @return 结果
     */
    @Override
    public int deleteMovieById(Long id)
    {
        return movieMapper.deleteMovieById(id);
    }
}
