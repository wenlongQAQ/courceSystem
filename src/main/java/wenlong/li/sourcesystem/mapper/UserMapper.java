package wenlong.li.sourcesystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import wenlong.li.sourcesystem.pojo.User;
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
