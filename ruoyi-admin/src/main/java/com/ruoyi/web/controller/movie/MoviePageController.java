package com.ruoyi.web.controller.movie;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.CommentPlusMapper;
import com.ruoyi.system.mapper.KeyValueMapper;
import com.ruoyi.system.mapper.MovieClickMapper;
import com.ruoyi.system.mapper.MoviePlusMapper;
import com.ruoyi.system.service.*;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class MoviePageController extends BaseController {

    @Autowired
    private IMovieService movieService;

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private ICollectService collectService;


    @Autowired
    private MoviePlusMapper moviePlusMapper;

    @Autowired
    private KeyValueMapper keyValueMapper;

    //

    @GetMapping("/moviedemo")
    @ResponseBody
    public String moviedemo(){
        String s = HttpUtils.sendGet("http://localhost:8000/recommend", "userid=2");
        return s;
    }





    @GetMapping("/index")
    public String getIndex(ModelMap model){
        //查询当前的
        LocalDateTime currentDate = LocalDateTime.now();
        QueryWrapper<KeyValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("DATE(create_time) = {0}", currentDate.toLocalDate());
        KeyValue keyValue = keyValueMapper.selectOne(queryWrapper);
        if(keyValue==null){
            KeyValue keyValue1 = new KeyValue();
            keyValue1.setKeym("click");
            keyValue1.setValuem(1L);
            keyValueMapper.insert(keyValue1);
        }else{
            keyValue.setValuem(keyValue.getValuem()+1);
            keyValueMapper.updateById(keyValue);
        }
        List<Movie> movies = movieService.selectMovieList(new Movie());
        List<Blog> blogs = blogService.selectBlogList(new Blog());
        List<Blog> blogs1 = blogs.subList(0, Math.min(blogs.size(), 4));
        List<Movie> movies1 = movies.subList(0, Math.min(7, movies.size()));

        List<Movie> sortedMovies = new ArrayList<>(movies);

        model.put("popular",movies1);
        //rate
        sortedMovies.sort(Comparator.comparingDouble(Movie::getRate).reversed());

        model.put("rate", sortedMovies.subList(0,Math.min(7,movies.size()))); // 使用排序后的列表
        List<Movie> movies2 = movieService.selectMovieRand();
        model.put("head",movies2);
        model.put("blogs",blogs1);

        // 推荐电影
        List<Movie> movies3 = new ArrayList<>();
        String rec = HttpUtils.sendGet("http://localhost:8000/recommend", "userid=2");
        if(rec.contains("[")){
            String[] elements = rec.substring(1, rec.length() - 1).split(", ");
            for(String s:elements){
                Movie movie = movieService.selectMovieById(Long.valueOf(s));
                movies3.add(movie);
            }
        }
        if(movies3.isEmpty()){
            List<Movie> movies4 = movieService.selectMovieRand();
            movies3.addAll(movies4);
        }
        model.put("rec",movies3);


        return "movie/index";
    }

    @Autowired
    private CommentPlusMapper commentPlusMapper;
    @Autowired
    private MovieClickMapper movieClickMapper;

    @Autowired
    private ISysDictDataService dictDataService;
    @Autowired
    private ISysDictTypeService dictTypeService;

    @PostMapping("/moviesearch")
    public String moviesearch(@RequestParam(defaultValue = "") String cate,@RequestParam String name,ModelMap model){
        Movie movie = new Movie();
        movie.setName(name);
        movie.setType(cate);
        List<Movie> movies = movieService.selectMovieList(movie);
        PageInfo<Movie> pageInfo = new PageInfo<>(movies);
        model.put("info",pageInfo);
        if(!"".equals(cate)&&"".equals(name)){
            model.put("flag",false);
        }
        return "movie/moviegridfw";
    }




    @GetMapping("/movieecharts")
    public String getEcharts(ModelMap model){

        List<KeyValue> keyValues = keyValueMapper.selectList(null);
        // 电影总数
        Long movieCount = moviePlusMapper.selectCount(null);
        // 评论总数
        Long commentCount = commentPlusMapper.selectCount(null);
        model.put("moviecount",movieCount);
        model.put("commentcount",commentCount);
        return "movie/echarts";
    }

    @GetMapping("/movieblog")
    public String getBlog(@RequestParam(required = false,defaultValue = "1") Integer page,ModelMap model){
        PageHelper.startPage(page,9);
        List<Blog> blogs = blogService.selectBlogList(new Blog());
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.put("info",pageInfo);
        clearPage();
        return "movie/bloggrid";
    }

    @GetMapping("/movies")
    public String getMovies(@RequestParam(required = false,defaultValue = "1") Integer page, ModelMap model){
        PageHelper.startPage(page,18);
        List<Movie> movies = movieService.selectMovieList(new Movie());
        PageInfo<Movie> pageInfo = new PageInfo<>(movies);
        model.put("info",pageInfo);
        System.out.println(pageInfo);
        clearPage();
        return "movie/moviegridfw";
    }


    @GetMapping("/movie/collect/{id}")
    public String CollectMovie(@PathVariable Long id,HttpSession session){
        MovieUser user = (MovieUser) session.getAttribute("user");
        Collect collect = new Collect();
        collect.setUserId(user.getId());
        collect.setMovieId(id);
        collectService.insertCollect(collect);
        String url = "redirect:/movie/"+id.toString();
        return url;

    }

    @GetMapping("/movie/{id}")
    public String getMovieSingle(@PathVariable Integer id,Map<String,Object>map,HttpSession session,@RequestParam(required = false,defaultValue = "1") Integer page){
        Object user = session.getAttribute("user");
        if(user!=null){
            MovieUser user1 = (MovieUser) user;
            Collect collect = new Collect();
            collect.setUserId(user1.getId());
            collect.setMovieId(Long.valueOf(id));
            List<Collect> collects = collectService.selectCollectList(collect);
            Comment comment = new Comment();
            comment.setMovieId(Long.valueOf(id));
            comment.setUserId(user1.getId());
            List<Comment> comments = commentService.selectCommentList(comment);
            if(!collects.isEmpty()){
                map.put("collect",collects.get(0));
            }
            if(!comments.isEmpty()){
                System.out.println(comments);
                map.put("comment",comments.get(0));
            }
        }
        Movie movie = movieService.selectMovieById(Long.valueOf(id));
        // 更新count
        movie.setCount(movie.getCount()+1);
        movieService.updateMovie(movie);
        String photos = movie.getPhotos();
        String[] split = photos.split(",");
        List<String> list = Arrays.asList(split);
        map.put("photos",list);
        map.put("movie", movie);
        PageHelper.startPage(page,5);
        List<Comment> comments = commentService.selectCommentList(new Comment());
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        clearPage();
        map.put("cominfo",pageInfo);
        return "movie/moviesingle";
    }

    @GetMapping("/blog/{id}")
    public String getBlogDetail(@PathVariable Integer id,ModelMap model){
        Blog blog = blogService.selectBlogById(Long.valueOf(id));
        model.put("blog",blog);
        return "movie/blogdetail";
    }

    @GetMapping("/user/profile")
    public String UserProfile(){
        return "movie/userprofile";
    }


    @GetMapping("/user/favo")
    public String UserFavo(HttpSession session,ModelMap map){
        MovieUser user = (MovieUser) session.getAttribute("user");
        Collect collect = new Collect();
        collect.setUserId(user.getId());
        List<Collect> collects = collectService.selectCollectList(collect);
        List<Movie> list = new ArrayList<>();
        for(Collect col: collects){
            Movie movie = movieService.selectMovieById(col.getMovieId());
            list.add(movie);
        }
        System.out.println(list);
        map.put("col",list);
        return "movie/userfavoritegrid";
    }

    @GetMapping("/user/rate")
    public String UserRate(HttpSession session,ModelMap model){
        MovieUser user = (MovieUser) session.getAttribute("user");
        Comment comment = new Comment();
        comment.setUserId(user.getId());
        List<Comment> comments = commentService.selectCommentList(comment);
        List<Movie> movies = new ArrayList<>();
        for(Comment com :comments){
            movies.add(movieService.selectMovieById(com.getMovieId()));
        }
        model.put("comments",comments);
        model.put("movies",movies);
        return "movie/userrate";
    }

    @PostMapping("/movierating")
    public String MovieRating(@RequestParam Map<String,Object> map, HttpSession session, ModelMap model){
        Comment comment = new Comment();
        comment.setComment((String) map.get("comment"));
        comment.setUserId(Long.valueOf((String) map.get("userid")));
        Long movieid = Long.valueOf((String) map.get("movieid"));
        comment.setMovieId(movieid);
        Long rate = Long.valueOf((String) map.get("rate"));
        comment.setScore(rate);
        commentService.insertComment(comment);
        // 更新评分
        Movie movie = movieService.selectMovieById(movieid);
        movie.setRate((movie.getRate()+rate)/2);
        String url = "redirect:/movie/"+comment.getMovieId().toString();
        return url;
    }
}
