package com.shsxt.xm.server.service; 

import com.shsxt.xm.api.po.BasUser;
import com.shsxt.xm.api.service.IBasUserService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/** 
* BasUserServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>12/07/2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class BasUserServiceImplTest {
    @Resource
    private IBasUserService iBasUserService;

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: queryBasUserById(Integer id) 
* 
*/ 
@Test
public void saveQueryBasUserById() throws Exception {
//TODO: Test goes here...
   /* BasUser basUser = iBasUserService.queryBasUserById(1);
    System.out.println(basUser);*/

    iBasUserService.saveBasUser("18221049759","123456");

} 


} 
