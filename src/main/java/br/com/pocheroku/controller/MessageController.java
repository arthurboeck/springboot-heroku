package br.com.pocheroku.controller;

import br.com.pocheroku.client.chatbot.IChatBotClient;
import br.com.pocheroku.dto.ChatBotDTO;
import br.com.pocheroku.dto.MessageDTO;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Messages", value = "String messages")
@Validated
public class MessageController {

    private final IChatBotClient iChatBotClient;

    public MessageController(IChatBotClient iChatBotClient) {
        this.iChatBotClient = iChatBotClient;
    }

    @GetMapping("/hello")
    @ApiOperation(value = "Return default message 'Hello Dear World!'")
    @ApiResponse(code = 200, message = "Success")
    public ResponseEntity<MessageDTO> sayHello() {
        String msg = "Hello Dear World!";
        return ResponseEntity.ok().body(new MessageDTO(msg));
    }

    @GetMapping("/chatboot")
    @ApiOperation(value = "Chat with a ChatBootAPI")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 500, message = "Internal Error")
            })
    public ResponseEntity<ChatBotDTO> textToChatBot(
            @ApiParam(value = "Message to be send to chatboot", required = true)
            @NotNull(message = "Message can't be null or empty") @NotBlank(message = "Message can't be null or empty") @RequestParam(name = "message") String message) {
        return ResponseEntity.ok(iChatBotClient.getChatBootMessage(message));
    }
}

