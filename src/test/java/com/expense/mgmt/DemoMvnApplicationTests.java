package com.expense.mgmt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(properties = "spring.flyway.enabled=false")
class DemoMvnApplicationTests {

    @Test
    void contextLoads() {
    }

}
