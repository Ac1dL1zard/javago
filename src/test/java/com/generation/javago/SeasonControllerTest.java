package com.generation.javago;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
	public void test() {
		fail("Not yet implemented");
	}

}
