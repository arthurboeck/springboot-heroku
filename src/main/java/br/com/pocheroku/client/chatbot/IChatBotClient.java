package br.com.pocheroku.client.chatbot;

import br.com.pocheroku.dto.ChatBotDTO;

public interface IChatBotClient {
    ChatBotDTO getChatBootMessage(String myMessage);
}
