package br.com.pocheroku.utiltest;

import br.com.pocheroku.Application;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@ImportAutoConfiguration(FeignAutoConfiguration.class)
@AutoConfigureWireMock(port = 0)
@SpringBootTest(classes = Application.class)
public class UnitBaseTest {
}
