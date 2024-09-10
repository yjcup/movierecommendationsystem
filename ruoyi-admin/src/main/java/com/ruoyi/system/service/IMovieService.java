package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Movie;

/**
 * 电影信息管理Service接口
 * 
 * @author ruoyi
 * @date 2024-01-15
 */
public interface IMovieService 
{
    /**
     * 查询电影信息管理
     * 
     * @param id 电影信息管理主键
     * @return 电影信息管理
     */
    public Movie selectMovieById(Long id);


    public List<Movie> selectMovieRand();



    /**
     * 查询电影信息管理列表
     * 
     * @param movie 电影信息管理
     * @return 电影信息管理集合
     */
    public List<Movie> selectMovieList(Movie movie);

    /**
     * 新增电影信息管理
     * 
     * @param movie 电影信息管理
     * @return 结果
     */
    public int insertMovie(Movie movie);

    /**
     * 修改电影信息管理
     * 
     * @param movie 电影信息管理
     * @return 结果
     */
    public int updateMovie(Movie movie);

    /**
     * 批量删除电影信息管理
     * 
     * @param ids 需要删除的电影信息管理主键集合
     * @return 结果
     */
    public int deleteMovieByIds(String ids);

    /**
     * 删除电影信息管理信息
     * 
     * @param id 电影信息管理主键
     * @return 结果
     */
    public int deleteMovieById(Long id);
}
