package wenlong.li.sourcesystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wenlong.li.sourcesystem.common.R;
import wenlong.li.sourcesystem.pojo.Course;
import wenlong.li.sourcesystem.pojo.User;
import wenlong.li.sourcesystem.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/getall")
    public R<List<Course>> selectAllCourse(){
        List<Course> allCourse = courseService.list();
        for (Course course : allCourse) {
            if (Integer.valueOf(course.getVip())==1) {
                course.setVip("VIP课程");
            }else if(Integer.valueOf(course.getVip())==0) {
                course.setVip("非VIP课程");
            }
        }
        return R.success(allCourse,"查询成功");
    }
    @GetMapping("/getone/{id}")
    public R<Course> selectById(@PathVariable("id") Long id){
        LambdaQueryWrapper<Course> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Course::getId,id);
        Course one = courseService.getOne(lambdaQueryWrapper);
        return R.success(one,"查询成功");
    }
    @PutMapping("/edit")
    public R<Course> editCourse(@RequestBody Course course){
        LambdaQueryWrapper<Course> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Course::getId,course.getId());
        boolean result = courseService.update(course, lambdaQueryWrapper);
        if (result) {
            return R.success(null,"修改成功");
        }else {
            return R.error("修改失败");
        }
    }
    @PostMapping("/addcourse")
    public R<Course> addCourse(@RequestBody Course course){
        boolean result = courseService.save(course);
        if (result) {
            return R.success(null,"添加成功");
        }else {
            return R.error("请稍后再试");
        }

    }
    @GetMapping("/name/{courseName}")
    public R<List<Course>> selectByCourse(@PathVariable String courseName){
        LambdaQueryWrapper<Course> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Course::getCourseName,courseName);
        List<Course> allCourse = courseService.list(lambdaQueryWrapper);
        for (Course course : allCourse) {
            if (Integer.valueOf(course.getVip())==1) {
                course.setVip("VIP课程");
            }else if(Integer.valueOf(course.getVip())==0) {
                course.setVip("非VIP课程");
            }
        }
        return R.success(allCourse,"查询成功");
    }
    @DeleteMapping("/delete/{id}")
    public R<Course> deleteById(@PathVariable Long id){
        LambdaQueryWrapper<Course> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Course::getId,id);
        boolean result = courseService.remove(lambdaQueryWrapper);
        if (result) {
            return R.success(null,"删除成功");
        }else {
            return R.error("稍后再试");
        }
    }
    @GetMapping("/getbyidentity")
    public R<List<Course>> selectByIdentity(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");

        LambdaQueryWrapper<Course> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (Integer.valueOf(user.getIdentity())==0){
            lambdaQueryWrapper.eq(Course::getVip,user.getIdentity());
        }
        List<Course> courses = courseService.list(lambdaQueryWrapper);
        for (Course course : courses) {
            if (Integer.valueOf(course.getVip())==1) {
                course.setVip("VIP课程");
            }else if(Integer.valueOf(course.getVip())==0) {
                course.setVip("非VIP课程");
            }
        }
        return R.success(courses,"查询成功");
    }


}
