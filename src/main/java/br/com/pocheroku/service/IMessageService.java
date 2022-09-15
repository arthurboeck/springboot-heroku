package br.com.pocheroku.service;

import br.com.pocheroku.dto.MessageDTO;
import br.com.pocheroku.entity.MessageEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface IMessageService {

    MessageEntity create(MessageDTO messageDto);

    List<MessageEntity> findAll();

    List<MessageEntity> findByMessageDateGreaterThanEqual(LocalDateTime dateTime);
}
