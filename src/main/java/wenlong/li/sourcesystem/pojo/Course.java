package wenlong.li.sourcesystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Course {
    private String courseName;
    private String  vip;
    private String courseIntroduce;
    private String courseClassify;
    private Long id;
    private Double courseMoney;
}
