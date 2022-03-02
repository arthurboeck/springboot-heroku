package br.com.pocheroku.controller;

import br.com.pocheroku.dto.MessageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Messages", value = "String messages")
public class MessageController {

    @GetMapping("/hello")
    @ApiOperation(value = "Return default message 'Hello Dear World!'")
    @ApiResponse(code = 200, message = "Success")
    public ResponseEntity<MessageDTO> sayHello() {
        String msg = "Hello Dear World!";
        return ResponseEntity.ok().body(new MessageDTO(msg));
    }
}
