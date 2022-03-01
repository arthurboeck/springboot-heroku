package br.com.pocheroku.controller;

import br.com.pocheroku.dto.MessageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @GetMapping("/hello")
    public ResponseEntity<MessageDTO> sayHello() {
        String msg = "Hello Dear World!";
        return ResponseEntity.ok().body(new MessageDTO(msg));
    }
}
