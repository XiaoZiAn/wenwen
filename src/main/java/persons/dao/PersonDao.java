package persons.dao;

import lombok.Data;

import java.util.Date;

/**
 * @author xiaoxinga
 * @date 2018/9/10 11:19
 * @since
 */
@Data
@SuppressWarnings("serial")
public class PersonDao {
    private String personId;//用户ID
    private String personName;//用户昵称
    private String personAge;//用户年龄
    private String personSex;//用户性别
    private String personBirthday;//用户生日
    private String personPassword;//用户密码
    private Date createTime;//注册时间
}
