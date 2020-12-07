package com.bookService;

import org.hibernate.engine.spi.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersistenceContext.class})
@SpringBootTest
class BookServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
