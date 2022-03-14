package br.com.pocheroku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ActiveProfiles("it")
@AutoConfigureWireMock(port = 0)
@SpringBootTest(classes = Application.class)
public class FunctionalBaseTest {

    @Autowired
    private WebApplicationContext context;

    /**
     * Set spring security for IT tests
     */
    protected MockMvc getMockMvc() {
        final DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(context);

        builder.defaultRequest(get("/"))
                .defaultRequest(post("/"))
                .defaultRequest(put("/"))
                .defaultRequest(patch("/"))
                .defaultRequest(delete("/"));
        return builder
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }
}

