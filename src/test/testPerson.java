
import com.wenwen.System.service.SendEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.wenwen.persons.model.Person;
import com.wenwen.persons.mapper.PersonMapper;

import java.util.List;

/**
 * @author xiaoxinga
 * @date 2018/9/11 11:15
 * @since
 */
public class testPerson {
    @Autowired
    PersonMapper personMapper;
    @Autowired
    SendEmailService sendEmailService;

    @Test
    public void getAll() {
        List<Person> persons = personMapper.selectAll();
        System.out.println(persons);
    }

    public static void main(String args[]) {
        /*String password = "WX147822";
        String passworded = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(passworded);*/
        try{
            SendEmailService sendEmailService = new SendEmailService();
            sendEmailService.sendEmail("935725637@qq.com","测试","Hello world!");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
