package com.ruoyi.web.controller.movie;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.system.domain.Comment;
import com.ruoyi.system.domain.KeyValue;
import com.ruoyi.system.domain.Movie;
import com.ruoyi.system.domain.MovieClick;
import com.ruoyi.system.mapper.KeyValueMapper;
import com.ruoyi.system.mapper.MovieClickMapper;
import com.ruoyi.system.mapper.MoviePlusMapper;
import com.ruoyi.system.service.ICommentService;
import com.ruoyi.system.service.IMovieService;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysDictTypeService;
import com.ruoyi.web.controller.movie.util.WordCount;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class EchartController {

    @Autowired
    private ISysDictDataService dictDataService;
    @Autowired
    private ISysDictTypeService dictTypeService;

    @Autowired
    private IMovieService movieService;
    @Autowired
    private MovieClickMapper movieClickMapper;

    @Autowired
    private MoviePlusMapper moviePlusMapper;

    @Autowired
    private KeyValueMapper keyValueMapper;

    @Autowired
    private ICommentService commentService;


    @GetMapping("/moviechart6")
    public Map<String,Object> moviechart6(){
        QueryWrapper<KeyValue> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        List<KeyValue> keyValues = keyValueMapper.selectList(wrapper);
        List<String> xdata = new ArrayList<>();
        List<Long> ydata = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");

        for (KeyValue keyValue:keyValues){
            xdata.add(sdf.format(keyValue.getCreateTime()));
            ydata.add(keyValue.getValuem());
        }
        xdata.subList(0,Math.min(7,xdata.size()));
        ydata.subList(0,Math.min(7,ydata.size()));
        Map<String,Object> map = new HashMap<>();
        map.put("xdata",xdata);
        map.put("ydata",ydata);
        return map;
    }


    @GetMapping("/moviechart2")
    public List<Map<String, Object>> moviechart2() throws IOException {
        List<Comment> comments = commentService.selectCommentList(new Comment());
        StringBuffer sb = new StringBuffer();
        for(Comment comment:comments){
            sb.append(comment.getComment()).append(" ");
        }
        List<Map.Entry<String, Integer>> entries = WordCount.wordFrequency(sb.toString());
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : entries) {
            Map<String, Object> item = new HashMap<>();
            item.put("name", entry.getKey());
            item.put("value", entry.getValue());
            result.add(item);
        }
        result.subList(0,Math.min(result.size(),12));
        return result;
    }


    @GetMapping("/moviechart1")
    public List<Map<String,Object>> moviechart1(){
        List<Movie> movies = movieService.selectMovieList(new Movie());
        SysDictData dictData = new SysDictData();
        dictData.setDictType("movie_type");
        List<SysDictData> sysDictData = dictDataService.selectDictDataList(dictData);
        int a[] = new int[sysDictData.size()];
        for(Movie movie:movies){
            String type = movie.getType();
            String[] split = type.split(",");
            for(String s:split){
                a[Integer.parseInt(s)]++;
            }
        }
        List<Map<String,Object>> list = new ArrayList<>();
        for(int i =0;i<a.length;i++){
            list.add(createData(dictDataService.selectDictLabel("movie_type",String.valueOf(i)),a[i]));
        }
//        for(int i = 0;i<6;i++){
//            list.add(createData("123"+i,12+i));
//        }
        return list;
    }

    @GetMapping("/moviechart5")
    public List<Map<String,Object>> MovieChart5() {
        List<Movie> movies = movieService.selectMovieList(new Movie());
        int count[] = new int[11];
        for(Movie movie:movies){
            count[Math.toIntExact(movie.getRate())]++;
        }
        List<Map<String,Object>> res = new ArrayList<>();
        for(int i = 1 ;i<=10;i++){
            res.add(createData(String.valueOf(i),count[i]));
        }
        return res;
    }


    @GetMapping("/moviechart4")
    public Map<String,Object> MovieChart4(){
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.select("name","count").orderByDesc("count");
        List<Movie> movies = moviePlusMapper.selectList(wrapper);
        List<String> moviename = new ArrayList<>();
        List<Long> count = new ArrayList<>();
        for(Movie movie:movies){
            moviename.add(movie.getName());
            count.add(movie.getCount());
        }
        Map<String, Object> chartData = new HashMap<>();
        chartData.put("xdata",moviename);
        chartData.put("ydata",count);
        return chartData;
    }


    @GetMapping("/moviechart7")
    public  Map<String,Object> moviechart7(){
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.select("name","rate").orderByDesc("rate");
        List<Movie> movies = moviePlusMapper.selectList(wrapper);
        List<String> moviename = new ArrayList<>();
        List<Long> count = new ArrayList<>();
        for(Movie movie:movies){
            moviename.add(movie.getName());
            count.add(movie.getRate());
        }
        Map<String, Object> chartData = new HashMap<>();
        chartData.put("xdata",moviename);
        chartData.put("ydata",count);

        return chartData;
    }

    private Map<String, Object> createData(String name, int value) {
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("value", value);
        return data;
    }
}
