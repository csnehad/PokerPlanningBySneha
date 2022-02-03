package com.pokerapi;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.pokerrestapi.controller.SessionController;
import com.pokerrestapi.service.SessionService;

//@SpringBootTest
@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
class SessionControllerTests {

	@Autowired 
    private MockMvc mvc;
	
	 @MockBean 
	  private SessionService pokerSessionServiceMocked; 
	
	
	 @Ignore
	 @Test
		void getAllPokerSessionTest() {
		 
		 
		 
		}
	 
	@Ignore
	 @Test
	void contextLoads() {
	}

}
