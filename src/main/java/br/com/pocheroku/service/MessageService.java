package br.com.pocheroku.service;

import br.com.pocheroku.dto.MessageDTO;
import br.com.pocheroku.entity.MessageEntity;
import br.com.pocheroku.repository.IMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService implements IMessageService {

    @Autowired
    private IMessageRepository messageRepository;

    @Override
    public MessageEntity create(MessageDTO messageDto) {
        MessageEntity message = new MessageEntity(null, messageDto.getMessage(), LocalDateTime.now());
        return messageRepository.save(message);
    }

    @Override
    public List<MessageEntity> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public List<MessageEntity> findByMessageDateGreaterThanEqual(LocalDateTime dateTime) {
        return messageRepository.findByMessageDateGreaterThanEqual(dateTime);
    }
}
