package ua.rd.web.controller;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ua.rd.web.config.AppServletConfig;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppServletConfig.class)
public class NewControllerTest {

	private MockMvc mvc;
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testProcess() {
		//mvc.perform(get());
	}

}
