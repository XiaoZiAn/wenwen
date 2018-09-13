import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import persons.dao.PersonDao;

import java.util.List;

/**
 * @author xiaoxinga
 * @date 2018/9/11 11:15
 * @since
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/ApplicationContext.xml")
public class testPerson {
    @Autowired
    persons.mapper.PersonMapper personMapper;
    @Test
    public void getAll (){
        List<PersonDao> persons =  personMapper.selectAll();
        System.out.println(persons);
    }
}
