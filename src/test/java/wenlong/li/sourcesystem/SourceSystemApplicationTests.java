package wenlong.li.sourcesystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wenlong.li.sourcesystem.pojo.Course;
import wenlong.li.sourcesystem.pojo.User;
import wenlong.li.sourcesystem.service.CourseService;
import wenlong.li.sourcesystem.service.UserService;

import java.util.List;

@SpringBootTest
class SourceSystemApplicationTests {
    @Autowired
    public CourseService courseService;
    @Test
    void contextLoads() {
        List<Course> list = courseService.list();
        for (Course course : list) {
            System.out.println(course);
        }
    }

}
