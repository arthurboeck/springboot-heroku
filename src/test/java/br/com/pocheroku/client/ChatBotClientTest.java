package br.com.pocheroku.client;

import br.com.pocheroku.client.chatbot.ChatBotClient;
import br.com.pocheroku.client.chatbot.IChatBotClient;
import br.com.pocheroku.dto.ChatBotDTO;
import br.com.pocheroku.utiltest.UnitBaseTest;
import br.com.pocheroku.utiltest.WireMockstubs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import({ChatBotClient.class})
class ChatBotClientTest extends UnitBaseTest {

    @Autowired
    private IChatBotClient iChatBotClient;

    @Test
    void mustReturnChatBotReply() {
        WireMockstubs.getChatBotReturn("Hello", "getChatBotApiReturn.json");

        ChatBotDTO chatBotReply = iChatBotClient.getChatBootMessage("Hello");

        assertEquals("Hello", chatBotReply.getMessage());
    }
}
