package com.generation.javago;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.generation.javago.model.repostiory.SeasonRepository;





@SpringBootTest
(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = JavagoApplication.class
)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class SeasonControllerTest {

	@Autowired
	MockMvc mock;

	@Autowired
	SeasonRepository cRepo;
	
	
	String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnaW5vQGdtYWlsLmNvbSIsImV4cCI6MTY5ODc4NDcyMywiaWF0IjoxNjk4NzY2NzIzfQ.dRaDm7I7xn6uvXbrbM1Lf6TWfX-hwFSVdwj9JnOsmGqDY8LcBGAM6QUAy8vRi9GpU24rrX9wX_krrJOtnaqnRg";

	@Test
	public void testGetAllSeasons() throws Exception 
	{
		mock.perform(MockMvcRequestBuilders.get("/api/seasons")  	
		.header("Authorization", "Bearer " + token))
		.andDo(print())											
		.andExpect(status().is(200));
		
		mock.perform(MockMvcRequestBuilders.get("/api/seasonsssss")
		.header("Authorization", "Bearer " + token))  	
		.andDo(print())											
		.andExpect(status().is(404));
		
		mock.perform(MockMvcRequestBuilders.get("/api/seasons/paperino")
		.header("Authorization", "Bearer " + token))  	
		.andDo(print())											
		.andExpect(status().is(400))
		.andExpect(MockMvcResultMatchers.content().string("Parametro non valido"));
	}

}
