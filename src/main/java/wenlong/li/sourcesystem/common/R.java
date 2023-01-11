package wenlong.li.sourcesystem.common;

import lombok.Data;

@Data
public class R<L> {
    private String msg;
    private Object data;
    private Integer code;
    public static R error(String msg){
        R r = new R();
        r.setMsg(msg);
        r.setCode(0);
        return r;
    }
    public static R success(Object obj,String msg){
        R r = new R();
        r.setMsg(msg);
        r.setCode(1);
        r.setData(obj);
        return r;
    }
}
