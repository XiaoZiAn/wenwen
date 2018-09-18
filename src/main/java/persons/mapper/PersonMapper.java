package persons.mapper;

import persons.dao.PersonDao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author xiaoxinga
 * @date 2018/9/10 11:14
 * @since
 */
public interface PersonMapper {
    int insert(@Param("person") PersonDao person);
    PersonDao selectByPersonId(@Param("personId") String personId);
    List<PersonDao> selectAll();
    String getPasswordById(@Param("personId") String personId);
}
