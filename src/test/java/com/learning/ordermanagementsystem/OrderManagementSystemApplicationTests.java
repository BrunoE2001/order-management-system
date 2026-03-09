package com.learning.ordermanagementsystem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(OutputCaptureExtension.class)
@SpringBootTest
class OrderManagementSystemApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testMain(CapturedOutput output) {
        OrderManagementSystemApplication.main(new String[]{
                "--spring.main.web-application-type=none", // No levanta Tomcat
                "--spring.main.lazy-initialization=true",  // No crea beans innecesarios
                "--spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration" // Evita conectar a DB
        });

        // Verificamos que en la consola aparezca el mensaje típico de inicio de Spring Boot
        assertThat(output.getOut()).contains("Started OrderManagementSystemApplication");
    }
}
