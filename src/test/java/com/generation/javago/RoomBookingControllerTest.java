package com.generation.javago;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
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

	String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnaW5vQGdtYWlsLmNvbSIsImV4cCI6MTY5ODk1NDAyNywiaWF0IjoxNjk4OTM2MDI3fQ.of-xnsD-GVu2CEmMuK5YX6WSUYgn7prh2y-JYQYvlswCF5KFygPXWQj5Azt5uMAcyXszYMCMgPlLFPTCmF-fJA";

	@Test
	public void testGetAll() throws Exception 
	{
				
				mock.perform(MockMvcRequestBuilders.get("/api/bookings/user/1")  	
				.header("Authorization", "Bearer " + token))
				.andDo(print())											
				.andExpect(status().is(200));
				
				mock.perform(MockMvcRequestBuilders.get("/api/bookings/user/999")  	
				.header("Authorization", "Bearer " + token))
				.andDo(print())											
				.andExpect(status().is(404));
						
				
				mock.perform(MockMvcRequestBuilders.get("/api/bokkkkkkings/user")
				.header("Authorization", "Bearer " + token))  	
				.andDo(print())											
				.andExpect(status().is(404));
				
				mock.perform(MockMvcRequestBuilders.get("/api/bookings/user/paperino")
				.header("Authorization", "Bearer " + token))  	
				.andDo(print())											
				.andExpect(status().is(400))
				.andExpect(MockMvcResultMatchers.content().string("Parametro non valido"));
	}
	

	@Test
	public void testGetOne() throws Exception 
	{
				
				mock.perform(MockMvcRequestBuilders.get("/api/bookings/1/customers/1")  	
				.header("Authorization", "Bearer " + token))
				.andDo(print())											
				.andExpect(status().is(200));
				
				mock.perform(MockMvcRequestBuilders.get("/api/bookings/1/customers/999")  	
				.header("Authorization", "Bearer " + token))
				.andDo(print())											
				.andExpect(status().is(404));
				
				mock.perform(MockMvcRequestBuilders.get("/api/bookings/999/customers/1")  	
				.header("Authorization", "Bearer " + token))
				.andDo(print())											
				.andExpect(status().is(404));
						
				
				mock.perform(MockMvcRequestBuilders.get("/api/bookings/999/customers/papero")
				.header("Authorization", "Bearer " + token))  	
				.andDo(print())											
				.andExpect(status().is(400))
				.andExpect(MockMvcResultMatchers.content().string("Parametro non valido"));
	}
	
//	@Test
//	public void insert() throws Exception 
//	{
//				String dtoValido = "{\r\n"
//						+ "    \"checkin_date\": \"2024-11-01\",\r\n"
//						+ "    \"checkout_date\": \"2024-11-05\",\r\n"
//						+ "    \"n_guest\": 4,\r\n"
//						+ "    \"saved\": false\r\n"
//						+ "}"; 
//				String dtoNonValido = "{\r\n"
//						+ "    \"checkin_date\": \"2024-11-01\",\r\n"
//						+ "    \"checkout_date\": \"2024-11-05\",\r\n"
//						+ "    \"n_guest\": 0,\r\n"
//						+ "    \"saved\": false\r\n"
//						+ "}"; 
//		
//		
//				
//				mock.perform(MockMvcRequestBuilders.post("/api/bookings/customers/1/rooms/1")
//				.header("Authorization", "Bearer " + token)
//				.content(dtoValido)
//				.contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().is(200));
//				mock.perform(MockMvcRequestBuilders.post("/api/bookings/customers/1/rooms/1")
//				.header("Authorization", "Bearer " + token)
//				.content(dtoValido)
//				.contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().is(406));
//					  
//				mock.perform(MockMvcRequestBuilders.post("/api/bookings/customers/1/rooms/1")
//				.header("Authorization", "Bearer " + token)
//				.content(dtoNonValido)
//				.contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().is(406));				
//			
//				
//	}
	
	

}
