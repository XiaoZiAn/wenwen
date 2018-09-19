package persons.mapper;

import persons.model.Person;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author xiaoxinga
 * @date 2018/9/10 11:14
 * @since
 */
public interface PersonMapper {
    int insert(@Param("person") Person person);
    Person selectByPersonId(@Param("personId") String personId);
    List<Person> selectAll();
    String getPasswordById(@Param("personId") String personId);
}
