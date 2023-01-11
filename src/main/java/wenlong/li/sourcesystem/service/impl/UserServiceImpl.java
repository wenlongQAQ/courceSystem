package wenlong.li.sourcesystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import wenlong.li.sourcesystem.mapper.UserMapper;
import wenlong.li.sourcesystem.pojo.User;
import wenlong.li.sourcesystem.service.UserService;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
