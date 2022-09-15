package br.com.pocheroku.controller;

import br.com.pocheroku.client.chatbot.IChatBotClient;
import br.com.pocheroku.dto.ChatBotDTO;
import br.com.pocheroku.dto.MessageDTO;
import br.com.pocheroku.service.IMessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static br.com.pocheroku.utiltest.UnitBaseTest.toJson;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MessageController.class)
@AutoConfigureMockMvc
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IChatBotClient iChatBotClient;

    @MockBean
    IMessageService iMessageService;

    @Test
    void checkSayHelloIsUp() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Hello Dear World!"));
    }

    @Test
    void checkTextToChatBotApi() throws Exception {
        when(this.iChatBotClient.getChatBootMessage("Hi"))
                .thenReturn(new ChatBotDTO("Heyy"));

        mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/chatboot").param("message", "Hi").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Heyy"));
    }

    @Test
    void checkSendMessage() throws Exception {
        MessageDTO messageDTO = new MessageDTO("teste");

        mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(messageDTO)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }
}
