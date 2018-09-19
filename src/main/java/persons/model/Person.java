package persons.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * @author xiaoxinga
 * @date 2018/9/10 11:19
 * @since
 */
@Data
@SuppressWarnings("serial")
public class Person {
    private String personId;//用户ID
    @NotEmpty(message="{personName.notEmpty}")
    private String personName;//用户昵称
    private String personSex;//用户性别
    private String personBirthday;//用户生日
    @NotEmpty(message="{personEmail.notEmpty}")
    @Email(message="{user_email.wrong}")
    private String personEmail;//用户邮箱
    @NotEmpty(message="{personPassword.notEmpty}")
    private String personPassword;//用户密码
    private Date createTime;//注册时间
}
