package wenlong.li.sourcesystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wenlong.li.sourcesystem.common.R;
import wenlong.li.sourcesystem.pojo.User;
import wenlong.li.sourcesystem.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param request
     * @param user 封装前端传来的用户信息
     * @return 返回一个R对象 封装用户的信息以及后端消息
     */
    @PostMapping("/login")
    public R<User> userLogin(HttpServletRequest request, @RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,user.getUsername()).eq(User::getPassword,user.getPassword());
        User one = userService.getOne(lambdaQueryWrapper);
        if (one!=null){
            System.out.println(one.getId());
            request.getSession().setAttribute("user",one);
            log.info(String.valueOf(one.getIdentity()));
        }
        if (one==null){
            return R.error("账号或密码错误");
        }
        return R.success(one,"登陆成功");
    }

    /**
     *
     * @param user 封装前端传过来的数据
     * @return 返回一个封装了user对象和后端数据的R对象
     */
    @PostMapping("/register")
    public R<User> userRegister(@RequestBody User user){

        boolean result = userService.save(user);
        if (result) {
            return R.success("","注册成功");
        }else {
            return R.error("请稍后再试");
        }
    }
    @GetMapping("/getcache")
    public R<User> getUserCache(HttpServletRequest request){
        return R.success(request.getSession().getAttribute("user"),"查询成功");
    }
    @GetMapping("/selectalluser")
    public R<List<User>> selectAllUser(){
        List<User> list = userService.list();
        for (User user : list) {
            if (Integer.valueOf(user.getIdentity())==0) {
                user.setIdentity("非VIP用户");
            } else if (Integer.valueOf(user.getIdentity()) == 1) {
                user.setIdentity("VIP用户");
            } else if (Integer.valueOf(user.getIdentity()) == 2) {
                user.setIdentity("管理员用户");
            }
        }
        return R.success(list,"查询成功");
    }
    @GetMapping("/getbyid/{id}")
    public R<User> selectById(@PathVariable Long id){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getId,id);
        User one = userService.getOne(lambdaQueryWrapper);
        return R.success(one,"查询成功");
    }
    @PutMapping("/update")
    public R<User> updateUser(@RequestBody User user){
        boolean result = userService.updateById(user);
        if (result) {
            return R.success(null,"修改成功");
        }else {
            return R.error("请重试");
        }
    }
    @DeleteMapping("/delete/{id}")
    public R<User> deleteUser(@PathVariable Long id){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getId,id);
        boolean remove = userService.remove(lambdaQueryWrapper);
        if (remove) {
            return R.success(null,"成功删除");
        }else {
            return R.error("请重试");
        }
    }
    @GetMapping("/selectbyname/{name}")
    public R<List<User>> selectByName(@PathVariable String name){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,name);
        User one = userService.getOne(lambdaQueryWrapper);
        List<User> users = new ArrayList<>();
        users.add(one);
        if (one!=null) {
            return R.success(users,"查询成功");
        }else {
            return R.error("请重试");
        }
    }
    @PostMapping("/adduser")
    public R<User> addUser(@RequestBody User user){
        boolean save = userService.save(user);
        if (save) {
            return R.success(null,"添加成功");
        }else {
            return R.error("请重试");
        }
    }

}
