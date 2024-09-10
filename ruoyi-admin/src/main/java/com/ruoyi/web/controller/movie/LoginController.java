package com.ruoyi.web.controller.movie;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.MovieUser;
import com.ruoyi.system.service.impl.MovieUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {


    @Autowired
    private MovieUserServiceImpl movieUserService;
    @PostMapping("/movielogin")
    @ResponseBody
    public AjaxResult MvoieLogin(@RequestParam Map<String,Object> map, HttpSession session){
        MovieUser user = new MovieUser();
        user.setUsername((String) map.get("username"));
        user.setPassword((String) map.get("passowrd"));
        List<MovieUser> movieUsers = movieUserService.selectMovieUserList(user);
        if(movieUsers.isEmpty()){
            System.out.println("账号密码错误");
            return AjaxResult.error();
        }
        else{
            System.out.println(movieUsers.get(0));
            session.setAttribute("user",movieUsers.get(0));
        }
        return AjaxResult.success();
    }


    @PostMapping("/moviesignup")
    public String MovieSignup(@RequestParam Map<String,Object>map){
        MovieUser user = new MovieUser();
        user.setUsername((String) map.get("username"));
        user.setEmail((String) map.get("email"));
        user.setPassword((String) map.get("password"));
        user.setImg("/profile/avatar/2024/01/13/blob_20240113153312A001.png");
        movieUserService.insertMovieUser(user);
        return "redirect:/index";
    }

    @GetMapping("/logout")
    @ResponseBody
    public AjaxResult Logout(HttpSession session){
        session.removeAttribute("user");
        System.out.println(session.getAttribute("user"));
        System.out.println("asdfsdf");
        return AjaxResult.success();

    }


}
