package persons.controller;

import org.springframework.beans.factory.annotation.Autowired;
import persons.mapper.PersonMapper;
/**
 * @author xiaoxinga
 * @date 2018/9/10 11:53
 * @since
 */
public class PersonController {
    @Autowired
    PersonMapper syncSellerMobileService;
}
