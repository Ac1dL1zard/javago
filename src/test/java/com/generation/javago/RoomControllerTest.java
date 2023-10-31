package com.generation.javago;

import static org.junit.Assert.fail;
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

import com.generation.javago.model.repostiory.RoomRepository;




@SpringBootTest
(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = JavagoApplication.class
)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class RoomControllerTest {

	@Autowired
	MockMvc mock;

	@Autowired
	RoomRepository repo;
	
	String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnaW5vQGdtYWlsLmNvbSIsImV4cCI6MTY5ODc4NDcyMywiaWF0IjoxNjk4NzY2NzIzfQ.dRaDm7I7xn6uvXbrbM1Lf6TWfX-hwFSVdwj9JnOsmGqDY8LcBGAM6QUAy8vRi9GpU24rrX9wX_krrJOtnaqnRg";

	@Test
	public void testGetAllRooms() throws Exception 
	{
		mock.perform(MockMvcRequestBuilders.get("/api/rooms/1")  	
		.header("Authorization", "Bearer " + token))
		.andDo(print())											
		.andExpect(status().is(200));
		
		mock.perform(MockMvcRequestBuilders.get("/api/roomssss")
		.header("Authorization", "Bearer " + token))  	
		.andDo(print())											
		.andExpect(status().is(404));
		
		mock.perform(MockMvcRequestBuilders.get("/api/rooms/paperino")
		.header("Authorization", "Bearer " + token))  	
		.andDo(print())											
		.andExpect(status().is(400))
		.andExpect(MockMvcResultMatchers.content().string("Parametro non valido"));
	}
	
	@Test
	public void testGetOneRoom() throws Exception
	{
		mock.perform(MockMvcRequestBuilders.get("/api/rooms/1")  	
				.header("Authorization", "Bearer " + token))
				.andDo(print())											
				.andExpect(status().is(200))
				.andExpect(MockMvcResultMatchers.content().json(jsonValido));		
				
		mock.perform(MockMvcRequestBuilders.get("/api/rooms/999")
		.header("Authorization", "Bearer " + token))  	
		.andDo(print())											
		.andExpect(status().is(404))
		.andExpect(MockMvcResultMatchers.content().string("Room not found"));
		
		
	}
	
	private String jsonValido="{\r\n"
			+ "    \"id\": 1,\r\n"
			+ "    \"img_url\": \"url_immagine_stanza.jpg\",\r\n"
			+ "    \"name\": \"Stanza Standard\",\r\n"
			+ "    \"capacity\": 2,\r\n"
			+ "    \"basePrice\": 100.0,\r\n"
			+ "    \"notes\": \"Stanza con vista sul giardino.\",\r\n"
			+ "    \"bookings\": [\r\n"
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
			+ "    ],\r\n"
			+ "    \"photoDTO\": []\r\n"
			+ "}"; 
	
	@Test 
	public void testUpdateOneRoom() throws Exception
	{ 
		String dtoValido="{\r\n"
				+ "    \"id\": 4,\r\n"
				+ "    \"img_url\": \"url_immagine_stanza.jpg\",\r\n"
				+ "    \"name\": \"Stanza meglio\",\r\n"
				+ "    \"capacity\": 3,\r\n"
				+ "    \"basePrice\": 110.0,\r\n"
				+ "    \"notes\": \"Stanza con vista sul mare.\"\r\n"
				+ "}";
		
		String dtoNonValido="{\r\n"
				+ "    \"id\": 4,\r\n"
				+ "    \"img_url\": \"url_immagine_stanza.jpg\",\r\n"
				+ "    \"name\": \"Stanza meglio\",\r\n"
				+ "    \"capacity\": 3,\r\n"
				+ "    \"notes\": \"Stanza con vista sul mare.\"\r\n"
				+ "}";
	
		
		
		
		mock.perform(MockMvcRequestBuilders.put("/api/rooms/4")
			.header("Authorization", "Bearer " + token)
			.content(dtoValido)
			.contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is(200));
		
		
		mock.perform(MockMvcRequestBuilders.put("/api/rooms/999")
				.header("Authorization", "Bearer " + token)
				.content(dtoNonValido)
				.contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(404))
				.andExpect(MockMvcResultMatchers.content().string("Room to update not found"));
		  
		mock.perform(MockMvcRequestBuilders.put("/api/rooms/4")
				.header("Authorization", "Bearer " + token)
				.content(dtoNonValido)
				.contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(406));
	
	}
	
	@Test 
	public void testInsertRoom() throws Exception
	{ 
		
		String dtoValido="{\r\n"
				+ "    \"img_url\": \"url_immagine_stanza.jpg\",\r\n"
				+ "    \"name\": \"Stanza bella bella\",\r\n"
				+ "    \"capacity\": 3,\r\n"
				+ "    \"basePrice\": 120.0,\r\n"
				+ "    \"notes\": \"Stanza con vista circo.\"\r\n"
				+ "}";
		
		String dtoNonValido="{\r\n"
				+ "    \"img_url\": \"url_immagine_stanza.jpg\",\r\n"
				+ "    \"name\": \"Stanza bruttaEx\",\r\n"
				+ "    \"capacity\": 3,\r\n"
				+ "    \"notes\": \"Stanza con vista sul mare.\"\r\n"
				+ "}";
		
		
		mock.perform(MockMvcRequestBuilders.post("/api/rooms")
				.header("Authorization", "Bearer " + token)
				.content(dtoValido)
				.contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200));
			  
			mock.perform(MockMvcRequestBuilders.post("/api/rooms")
					.header("Authorization", "Bearer " + token)
					.content(dtoNonValido)
					.contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
					.andExpect(status().is(406));
			
	}
	

}
