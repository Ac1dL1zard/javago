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

import com.generation.javago.model.repostiory.RoomBookingRepository;

@SpringBootTest
(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = JavagoApplication.class
)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class RoomBookingControllerTest 
{
	@Autowired
	MockMvc mock;
	@Autowired
	RoomBookingRepository repo;

	String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjaWFvQGNpYW8uaXQiLCJleHAiOjE2OTg3Nzc5OTMsImlhdCI6MTY5ODc1OTk5M30.3bz4SYrdQEGYCmxQga1_2HGuycaPAK_Cljng26B3zjIpzWglO6IEYWR2eljVwMMMLjMaueP-lcEYXTROBE-_pw";

	@Test
	public void testGetAll() throws Exception 
	{
		mock.perform(MockMvcRequestBuilders.get("/api/bookings/6")  	
				.header("Authorization", "Bearer " + token))
				.andDo(print())											
				.andExpect(status().is(200));
				
				mock.perform(MockMvcRequestBuilders.get("/api/bokkkkkkings")
				.header("Authorization", "Bearer " + token))  	
				.andDo(print())											
				.andExpect(status().is(404));
				
				mock.perform(MockMvcRequestBuilders.get("/api/bookings/paperino")
				.header("Authorization", "Bearer " + token))  	
				.andDo(print())											
				.andExpect(status().is(400))
				.andExpect(MockMvcResultMatchers.content().string("Parametro non valido"));
	}

}
