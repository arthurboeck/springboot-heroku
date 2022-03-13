package br.com.pocheroku.client.chatbot;

import br.com.pocheroku.dto.ChatBotDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class ChatBotClient implements IChatBotClient {

    private final IChatBotClient iChatBotClient;
    private final String bid;
    private final String key;
    private final String uid;

    public ChatBotClient(
            @Value("${service.ChatBootApi.bid}") String bid,
            @Value("${service.ChatBootApi.key}") String key,
            @Value("${service.ChatBootApi.uid}") String uid,
            IChatBotClient iChatBotClient) {
        this.bid = bid;
        this.key = key;
        this.uid = uid;
        this.iChatBotClient = iChatBotClient;
    }

    @Override
    public ChatBotDTO getChatBootMessage(String myMessage) {
        return iChatBotClient.getChatBootMessage(bid, key, uid, myMessage);
    }

    @FeignClient(url = "${service.ChatBootApi.url}", value = "chatBootApi")
    interface IChatBotClient {
        @GetMapping(path = "/get")
        ChatBotDTO getChatBootMessage(@RequestParam("bid") String bid,
                                      @RequestParam("key") String key,
                                      @RequestParam("uid") String uid,
                                      @RequestParam("msg") String myMessage);
    }
}
