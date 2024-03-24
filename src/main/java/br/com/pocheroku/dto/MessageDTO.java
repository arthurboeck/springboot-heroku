package br.com.pocheroku.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class MessageDTO {

    @Size(message = "Message must be between 1 and 80", min = 1, max = 80)
    private String message;

    public MessageDTO(String message) {
        this.message = message;
    }
}
