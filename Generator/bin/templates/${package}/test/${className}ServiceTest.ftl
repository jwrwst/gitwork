package ${package};


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.config.annotation.Service;
import ${package}.service.${className}Service;

/** 
 * Description: [XXXX的单元测试]
 * Created on ${date}
 * @author  <a href="mailto: XXXXXX@camelotchina.com">中文名字</a>
 * @version 1.0 
 * Copyright (c) ${year} 北京柯莱特科技有限公司 交付部 
 */
@Service
public class ${className}ServiceTest{

	private ${className}Service ${classNameLower}Service;
    ApplicationContext ctx;

	@Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("test.xml");
		${classNameLower}Service = (${className}Service) ctx.getBean("${classNameLower}Service");
	}
	
	
	@Test
	public void queryPageTest(){
		
	}
	
	@Test
	public void query${className}DTOByConditionTest(){
		
	}
	
	@Test
	public void saveTest(){
		
	}
	
	@Test
	public void editTest(){
		
	}
	
	@Test
	public void deleteTest(){
		
	}
}