package com.quality.quality_lab;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled("Setup: arrancaremos el contexto cuando tengamos DB de test (Testcontainers).")
@SpringBootTest
class QualityLabApplicationTests {

	@Test
	void contextLoads() {
	}

}
