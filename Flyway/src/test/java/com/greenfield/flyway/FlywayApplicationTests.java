package com.greenfield.flyway;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlywayApplicationTests {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String query = "select count(*) from person";
	private static final int FOUR = 4;

	@Test
	public void testInitialSettings() throws Exception {
		assertThat(jdbcTemplate.queryForObject(query, Integer.class)).isEqualTo(FOUR);
	}

}
