package com.generation.javago;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
	
	
	String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnaW5vQGdtYWlsLmNvbSIsImV4cCI6MTY5ODg2MDQ5MCwiaWF0IjoxNjk4ODQyNDkwfQ.us52E1uHOH6GwvRlqzcmt0z8iPNlxYE0slLXdF-CkKcRo3DEiAQFlSxehJlUWedXk28iT9nuH1xOi1_Gfr5W_g";

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
	
	
//	@Test
//	public void testGetOneSeason() throws Exception
//	{
//		mock.perform(MockMvcRequestBuilders.get("/api/seasons/1")  	
//				.header("Authorization", "Bearer " + token))
//				.andDo(print())											
//				.andExpect(status().is(200))
//				.andExpect(MockMvcResultMatchers.content().json(jsonValido));		
//				
//		mock.perform(MockMvcRequestBuilders.get("/api/seasons/999")
//		.header("Authorization", "Bearer " + token))  	
//		.andDo(print())											
//		.andExpect(status().is(404))
//		.andExpect(MockMvcResultMatchers.content().string("Season not found"));
//		
//		
//	}
	private String jsonValido="{\r\n"
			+ "    \"id\": 1,\r\n"
			+ "    \"season_name\": \"Stagione estiva\",\r\n"
			+ "    \"starting_date\": \"2023-06-01\",\r\n"
			+ "    \"end_date\": \"2023-08-31\",\r\n"
			+ "    \"price_modifer\": 20,\r\n"
			+ "    \"bookingsDTO\": [\r\n"
			+ "        {\r\n"
			+ "            \"id\": 1,\r\n"
			+ "            \"checkin_date\": \"2023-11-01\",\r\n"
			+ "            \"checkout_date\": \"2023-11-05\",\r\n"
			+ "            \"n_guest\": 2,\r\n"
			+ "            \"saved\": true,\r\n"
			+ "            \"price\": 500.0\r\n"
			+ "        },\r\n"
			+ "        {\r\n"
			+ "            \"id\": 4,\r\n"
			+ "            \"checkin_date\": \"2023-06-05\",\r\n"
			+ "            \"checkout_date\": \"2024-08-10\",\r\n"
			+ "            \"n_guest\": 2,\r\n"
			+ "            \"saved\": false,\r\n"
			+ "            \"price\": 48245.0\r\n"
			+ "        },\r\n"
			+ "        {\r\n"
			+ "            \"id\": 6,\r\n"
			+ "            \"checkin_date\": \"2023-06-05\",\r\n"
			+ "            \"checkout_date\": \"2024-08-10\",\r\n"
			+ "            \"n_guest\": 2,\r\n"
			+ "            \"saved\": false,\r\n"
			+ "            \"price\": 48245.0\r\n"
			+ "        }\r\n"
			+ "    ]\r\n"
			+ "}"; 
	
//	@Test 
//	public void testUpdateOneSeason() throws Exception
//	{ 
//		String dtoValido="{\r\n"
//				+ "    \"id\": 3,\r\n"
//				+ "    \"season_name\": \"Stagione bho\",\r\n"
//				+ "    \"starting_date\": \"2021-06-01\",\r\n"
//				+ "    \"end_date\": \"2022-08-31\",\r\n"
//				+ "    \"price_modifer\": 10\r\n"
//				+ "}";
//		
//		String dtoValido2="{\r\n"
//				+ "    \"id\": 999,\r\n"
//				+ "    \"season_name\": \"Stagione bho\",\r\n"
//				+ "    \"starting_date\": \"2021-06-01\",\r\n"
//				+ "    \"end_date\": \"2022-08-31\",\r\n"
//				+ "    \"price_modifer\": 20\r\n"
//				+ "}";
//		String dtoNonValido="{\r\n"
//				+ "    \"id\": 3,\r\n"
//				+ "    \"season_name\": \"Stagione bho\",\r\n"
//				+ "    \"starting_date\": \"2023-06-01\",\r\n"
//				+ "    \"end_date\": \"2022-08-31\",\r\n"
//				+ "    \"price_modifer\": 20\r\n"
//				+ "}";
//	
//		
//		
//		
//		mock.perform(MockMvcRequestBuilders.put("/api/seasons/3")
//			.header("Authorization", "Bearer " + token)
//			.content(dtoValido)
//			.contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
//			.andExpect(status().is(200));
//		
//		
//		mock.perform(MockMvcRequestBuilders.put("/api/seasons/999")
//				.header("Authorization", "Bearer " + token)
//				.content(dtoValido2)
//				.contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().is(404))
//				.andExpect(MockMvcResultMatchers.content().string("Season to update not found"));
//		  
//		mock.perform(MockMvcRequestBuilders.put("/api/seasons/3")
//				.header("Authorization", "Bearer " + token)
//				.content(dtoNonValido)
//				.contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().is(406));
//	
//	}
//	@Test 
//	public void testInsertSeason() throws Exception
//	{ 
//		
//		String dtoValido="{\r\n"
//				+ "    \"season_name\": \"Stagione nuovaaaaa\",\r\n"
//				+ "    \"starting_date\": \"2021-06-01\",\r\n"
//				+ "    \"end_date\": \"2022-08-31\",\r\n"
//				+ "    \"price_modifer\": 10\r\n"
//				+ "}";
//		
//		String dtoNonValido="{\r\n"
//				+ "    \"season_name\": \"Stagione vecchia\",\r\n"
//				+ "    \"starting_date\": \"2023-06-01\",\r\n"
//				+ "    \"end_date\": \"2022-08-31\",\r\n"
//				+ "    \"price_modifer\": 0\r\n"
//				+ "}";
//		
//		
//		
//		mock.perform(MockMvcRequestBuilders.post("/api/seasons")
//				.header("Authorization", "Bearer " + token)
//				.content(dtoValido)
//				.contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().is(200));
//			  
//			mock.perform(MockMvcRequestBuilders.post("/api/seasons")
//					.header("Authorization", "Bearer " + token)
//					.content(dtoNonValido)
//					.contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
//					.andExpect(status().is(406));
//			
//	}
//	@Test
//	public void testDeleteRoom() throws Exception
//	{
//		mock.perform(MockMvcRequestBuilders.delete("/api/seasons/7")  	
//		.header("Authorization", "Bearer " + token))
//		.andDo(print())											
//		.andExpect(status().is(200));
//		
//		mock.perform(MockMvcRequestBuilders.delete("/api/seasons/999")  	
//				.header("Authorization", "Bearer " + token))
//				.andDo(print())											
//				.andExpect(status().is(404));
//		
//		mock.perform(MockMvcRequestBuilders.delete("/api/seasons/paperino")  	
//				.header("Authorization", "Bearer " + token))
//				.andDo(print())											
//				.andExpect(status().is(400));
//	}
//	
	
}
