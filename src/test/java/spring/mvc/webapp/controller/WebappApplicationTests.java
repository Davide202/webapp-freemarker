package spring.mvc.webapp.controller;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import spring.mvc.webapp.Application;


@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = Application.class)
@Log4j2
class WebappApplicationTests {


	TestRestTemplate testRestTemplate = new TestRestTemplate();

	@Value("${server.port}")
	Integer port;



	@Test
	void contextLoads() {
		ResponseEntity<String> html = testRestTemplate.getForEntity(String.format("http://localhost:%s/registration/regione/Abruzzo",port),String.class);
		log.info(html.getBody());

	}

}
