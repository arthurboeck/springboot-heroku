package br.com.pocheroku.controller;

import br.com.pocheroku.FunctionalBaseTest;
import br.com.pocheroku.utiltest.WireMockstubs;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MessageControllerFunctionalTest extends FunctionalBaseTest {

    private static final String MESSAGE_PROP = "message";
    private static final String CHAT_BOT_API = "/api/v1/chatboot";
    private static final String CONSTRAINT_VIOLATION_MSG = "javax.validation.ConstraintViolationException: textToChatBot.message: Message can't be null or empty";


    @Test
    @Tag("functional")
    void mustReturnHello() throws Exception {
        getMockMvc().perform(get("/api/v1/hello")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(MESSAGE_PROP, equalTo("Hello Dear World!")));
    }

    @Test
    @Tag("functional")
    void mustReturnChatBotReplySuccess() throws Exception {
        setWireMockStub("Hello");
        getMockMvc().perform(get(CHAT_BOT_API).param(MESSAGE_PROP, "Hello")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(MESSAGE_PROP, equalTo("Hello")));
    }

    @Test
    @Tag("functional")
    void sendLargeStringMustReturnChatBotReplySuccess() throws Exception {
        String generatedString = RandomStringUtils.randomAlphabetic(999);
        setWireMockStub(generatedString);

        getMockMvc().perform(get(CHAT_BOT_API).param(MESSAGE_PROP, generatedString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(MESSAGE_PROP, equalTo("Hello")));
    }

    @Test
    @Tag("functional")
    void sendNullMustReturnChatBotBadRequest() throws Exception {
        String value = null;
        setWireMockStub("Hello");

        getMockMvc().perform(get(CHAT_BOT_API).param(MESSAGE_PROP, value)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isBadRequest(),
                        status().reason(equalToIgnoringCase("Required request parameter 'message' for method parameter type String is not present")));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", ""})
    @Tag("functional")
    void sendBlanckOrEmptyMustReturnChatBotBadRequest(String value) throws Exception {
        setWireMockStub("Hello");
        getMockMvc().perform(get(CHAT_BOT_API).param(MESSAGE_PROP, value)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isBadRequest(),
                        jsonPath("$.error").value(CONSTRAINT_VIOLATION_MSG),
                        jsonPath("$.timestamp")
                                .value(containsStringIgnoringCase(LocalDateTime.now().toString().substring(0, 20))));
    }

    private void setWireMockStub(String message) {
        WireMockstubs.getChatBotReturn(message, "getChatBotApiReturn.json");
    }
}
