package br.com.pocheroku.controller;

import br.com.pocheroku.client.chatbot.IChatBotClient;
import br.com.pocheroku.dto.ChatBotDTO;
import br.com.pocheroku.dto.MessageDTO;
import br.com.pocheroku.dto.StoredMessagesDTO;
import br.com.pocheroku.entity.MessageEntity;
import br.com.pocheroku.service.IMessageService;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Messages", value = "String messages")
@Validated
public class MessageController {

    private final IChatBotClient iChatBotClient;
    private final IMessageService iMessageService;

    public MessageController(
            IChatBotClient iChatBotClient,
            IMessageService iMessageService) {
        this.iChatBotClient = iChatBotClient;
        this.iMessageService = iMessageService;
    }


    @Autowired
    private ModelMapper modelMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @GetMapping("/hello")
    @ApiOperation(value = "Return default message 'Hello Dear World!'")
    @ApiResponse(code = 200, message = "Success")
    public ResponseEntity<MessageDTO> sayHello() {
        String msg = "Hello Dear World!";
        return ResponseEntity.ok().body(new MessageDTO(msg));
    }

    @GetMapping("/chatboot")
    @ApiOperation(value = "Chat with a ChatBootAPI")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity<ChatBotDTO> textToChatBot(
            @ApiParam(value = "Message to be send to chatboot", required = true)
            @NotNull(message = "Message can't be null or empty") @NotBlank(message = "Message can't be null or empty") @RequestParam(name = "message") String message) {
        return ResponseEntity.ok(iChatBotClient.getChatBootMessage(message));
    }

    @PostMapping("/send")
    @ApiOperation(value = "Send me a message")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity sendMeMessage(
            @RequestBody @Valid MessageDTO message) {
        MessageEntity messageSent = iMessageService.create(message);
        URI location =
                ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/v1/send/{id}")
                        .buildAndExpand(messageSent.getMessageId())
                        .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/listSentMessages")
    @ApiOperation(value = "Chat with a ChatBootAPI")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity<List<StoredMessagesDTO>> listSentMessages() {
        List<StoredMessagesDTO> messagesList =
                iMessageService.findAll().
                        stream()
                        .map(message -> modelMapper.map(message, StoredMessagesDTO.class))
                        .map(message -> modelMapper.map(message, StoredMessagesDTO.class))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(messagesList);
    }
}

