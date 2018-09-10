package persons.mapper;

import persons.dao.PersonDao;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiaoxinga
 * @date 2018/9/10 11:14
 * @since
 */
public interface PersonMapper {
    int insert(@Param("person") PersonDao person);
}
