package wenlong.li.sourcesystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import wenlong.li.sourcesystem.mapper.CourseMapper;
import wenlong.li.sourcesystem.pojo.Course;
import wenlong.li.sourcesystem.service.CourseService;
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
}
