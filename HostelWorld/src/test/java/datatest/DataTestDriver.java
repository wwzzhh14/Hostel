package datatest;

/**
 * Created by wzh on 16/8/28.
 */


import com.springmvc.dataservice.UserDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class DataTestDriver {

    @Resource
    UserDataService userDataService;



}