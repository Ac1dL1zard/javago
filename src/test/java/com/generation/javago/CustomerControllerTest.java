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

import com.generation.javago.model.repostiory.CustomerRepository;


@SpringBootTest
(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = JavagoApplication.class
)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CustomerControllerTest 
{
	@Autowired
	MockMvc mock;

	@Autowired
	CustomerRepository cRepo;
	
	String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjaWFvQGNpYW8uaXQiLCJleHAiOjE2OTg3Nzc5OTMsImlhdCI6MTY5ODc1OTk5M30.3bz4SYrdQEGYCmxQga1_2HGuycaPAK_Cljng26B3zjIpzWglO6IEYWR2eljVwMMMLjMaueP-lcEYXTROBE-_pw";
	@Test
	public void testGetAllCustomers() throws Exception 
	{
		mock.perform(MockMvcRequestBuilders.get("/api/customers/1")  	
		.header("Authorization", "Bearer " + token))
		.andDo(print())											
		.andExpect(status().is(200));
		
		mock.perform(MockMvcRequestBuilders.get("/api/customerrrrrrrrrrrrs")
		.header("Authorization", "Bearer " + token))  	
		.andDo(print())											
		.andExpect(status().is(404));
		
		mock.perform(MockMvcRequestBuilders.get("/api/customers/paperino")
		.header("Authorization", "Bearer " + token))  	
		.andDo(print())											
		.andExpect(status().is(400))
		.andExpect(MockMvcResultMatchers.content().string("Parametro non valido"));
	}
	
	@Test
	public void testGetOneCustomer() throws Exception
	{
		mock.perform(MockMvcRequestBuilders.get("/api/customers/1")  	
				.header("Authorization", "Bearer " + token))
				.andDo(print())											
				.andExpect(status().is(200))
				.andExpect(MockMvcResultMatchers.content().json(jsonValido));		
				
		mock.perform(MockMvcRequestBuilders.get("/api/customers/999")
		.header("Authorization", "Bearer " + token))  	
		.andDo(print())											
		.andExpect(status().is(404))
		.andExpect(MockMvcResultMatchers.content().string("Customer not found"));
		
		
	}

//	@Test 
//	public void testInsertCustomer() throws Exception
//	{ 
//		String dtoValido= 	"{\r\n"
//				+ "    \"id\": 5,\r\n"
//				+ "    \"name\": \"Zorro\",\r\n"
//				+ "    \"surname\": \"Neri\",\r\n"
//				+ "    \"dob\": \"1999-03-12\",\r\n"
//				+ "    \"userDTO\": {\r\n"
//				+ "        \"id\": 5,\r\n"
//				+ "        \"username\": \"tito@luca.it\",\r\n"
//				+ "        \"password\": \"$2a$10$g2rv9Nw7SnVpOYO71dpdouAWUo8nB/IId47dvb5JuwO3BUoQ4wRKG\",\r\n"
//				+ "        \"employed\": false\r\n"
//				+ "    }\r\n"
//				+ "}";
//		
//		String dtoNonValido= 	"{\r\n"
//				+ "    \"id\": 5,\r\n"
//				+ "    \"name\": \"Zorro\",\r\n"
//				+ "    \"surname\": \"Neri\",\r\n"
//				+ "    \"dob\": \"1899-03-12\",\r\n"
//				+ "    \"userDTO\": {\r\n"
//				+ "        \"id\": 5,\r\n"
//				+ "        \"username\": \"tito@luca.it\",\r\n"
//				+ "        \"password\": \"$2a$10$g2rv9Nw7SnVpOYO71dpdouAWUo8nB/IId47dvb5JuwO3BUoQ4wRKG\",\r\n"
//				+ "        \"employed\": false\r\n"
//				+ "    }\r\n"
//				+ "}";
//		String dtoWithoutUser = "{\r\n"
//				+ "    \"id\":5,\r\n"
//				+ "    \"name\": \"Zorro\",\r\n"
//				+ "    \"surname\": \"Neri\",\r\n"
//				+ "    \"dob\": \"1999-03-12\",\r\n"
//				+ "    \"userDTO\": \r\n"
//				+ "    {\r\n"
//				+ "    }\r\n"
//				+ "}";
//		
//		mock.perform(MockMvcRequestBuilders.post("/api/customers")
//			.header("Authorization", "Bearer " + token)
//			.content(dtoValido)
//			.contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
//			.andExpect(status().is(200));
//		  
//		mock.perform(MockMvcRequestBuilders.post("/api/customers")
//				.header("Authorization", "Bearer " + token)
//				.content(dtoNonValido)
//				.contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().is(406));
//		mock.perform(MockMvcRequestBuilders.post("/api/customers")
//				.header("Authorization", "Bearer " + token)
//				.content(dtoWithoutUser)
//				.contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().is(406));
//	}
	
	
	
	
//	@Test
//	public void testDeleteCustomer() throws Exception
//	{
//		mock.perform(MockMvcRequestBuilders.delete("/api/customers/6")  	
//		.header("Authorization", "Bearer " + token))
//		.andDo(print())											
//		.andExpect(status().is(200));
//		
//		mock.perform(MockMvcRequestBuilders.delete("/api/customers/999")  	
//				.header("Authorization", "Bearer " + token))
//				.andDo(print())											
//				.andExpect(status().is(404));
//		
//		mock.perform(MockMvcRequestBuilders.delete("/api/customers/paperino")  	
//				.header("Authorization", "Bearer " + token))
//				.andDo(print())											
//				.andExpect(status().is(400));
//	}
	
	@Test 
	public void testUpdateOneCustomer() throws Exception
	{ 
		String dtoValido= 	"{\r\n"
				+ "    \"id\": 6,\r\n"
				+ "    \"name\": \"Mirko\",\r\n"
				+ "    \"surname\": \"Simpaticone\",\r\n"
				+ "    \"dob\": \"1999-03-12\",\r\n"
				+ "    \"userDTO\": {\r\n"
				+ "        \"id\": 6,\r\n"
				+ "        \"username\": \"ciao@ciao.it\",\r\n"
				+ "        \"password\": \"$2a$10$a3/wHoLrreurQoX9H.QMnONVXa8TkfW1sfVUrMAdY5uTg0OZh.EIS\",\r\n"
				+ "        \"employed\": false\r\n"
				+ "    }\r\n"
				+ "}";
		
		String dtoNonValido2= 	"{\r\n"
				+ "    \"id\": 6,\r\n"
				+ "    \"name\": \"Zarro\",\r\n"
				+ "    \"surname\": \"Nori\",\r\n"
				+ "    \"dob\": \"2001-03-12\",\r\n"
				+ "    \"userDTO\": {\r\n"
				+ "        \"id\": 6,\r\n"
				+ "        \"username\": \"tito@luca.it\",\r\n"
				+ "        \"password\": \"$10$a3/wHoLrreurQoX9H.QMnONVXa8TkfW1sfVUrMAdY5uTg0OZh.EIS\",\r\n"
				+ "        \"employed\": false\r\n"
				+ "    }\r\n"
				+ "}";
		
		String dtoNonValido= 	"{\r\n"
				+ "    \"id\": 6,\r\n"
				+ "    \"name\": \"Zirro\",\r\n"
				+ "    \"surname\": \"Negri\",\r\n"
				+ "    \"dob\": \"1899-03-12\",\r\n"
				+ "    \"userDTO\": {\r\n"
				+ "        \"id\": 6,\r\n"
				+ "        \"username\": \"tito@luca.it\",\r\n"
				+ "        \"password\": \"$10$a3/wHoLrreurQoX9H.QMnONVXa8TkfW1sfVUrMAdY5uTg0OZh.EIS\",\r\n"
				+ "        \"employed\": false\r\n"
				+ "    }\r\n"
				+ "}";
		String dtoWithoutUser = "{\r\n"
				+ "    \"id\": 6,\r\n"
				+ "    \"name\": \"Zorro\",\r\n"
				+ "    \"surname\": \"Neri\",\r\n"
				+ "    \"dob\": \"1999-03-12\",\r\n"
				+ "    \"userDTO\": \r\n"
				+ "    {\r\n"
				+ "    }\r\n"
				+ "}";
		
		mock.perform(MockMvcRequestBuilders.put("/api/customers/6")
			.header("Authorization", "Bearer " + token)
			.content(dtoValido)
			.contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is(200));
		
		mock.perform(MockMvcRequestBuilders.put("/api/customers/6")
				.header("Authorization", "Bearer " + token)
				.content(dtoNonValido2)
				.contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(406));
		  
		mock.perform(MockMvcRequestBuilders.put("/api/customers/6")
				.header("Authorization", "Bearer " + token)
				.content(dtoNonValido)
				.contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(406));
		mock.perform(MockMvcRequestBuilders.put("/api/customers/6")
				.header("Authorization", "Bearer " + token)
				.content(dtoWithoutUser)
				.contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(406));
	}
	
	private String jsonValido="{\r\n"
			+ "    \"id\": 1,\r\n"
			+ "    \"name\": \"Laura\",\r\n"
			+ "    \"surname\": \"Bianchi\",\r\n"
			+ "    \"dob\": \"1988-03-12\",\r\n"
			+ "    \"roomBookingsDTO\": [\r\n"
			+ "        {\r\n"
			+ "            \"id\": 1,\r\n"
			+ "            \"checkin_date\": \"2023-11-01\",\r\n"
			+ "            \"checkout_date\": \"2023-11-05\",\r\n"
			+ "            \"n_guest\": 2,\r\n"
			+ "            \"saved\": true,\r\n"
			+ "            \"price\": 500.0\r\n"
			+ "        }\r\n"
			+ "    ],\r\n"
			+ "    \"userDTO\": {\r\n"
			+ "        \"id\": 3,\r\n"
			+ "        \"username\": \"utente2@example.com\",\r\n"
			+ "        \"password\": \"password_utente2\",\r\n"
			+ "        \"employed\": false\r\n"
			+ "    }\r\n"
			+ "}";

}
