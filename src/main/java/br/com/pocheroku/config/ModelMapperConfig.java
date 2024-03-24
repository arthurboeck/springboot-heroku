package br.com.pocheroku.config;

import br.com.pocheroku.dto.StoredMessagesDTO;
import br.com.pocheroku.entity.MessageEntity;
import org.hibernate.engine.spi.ManagedEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(StoredMessagesDTO.class, ManagedEntity.class);
        modelMapper.createTypeMap(MessageEntity.class, StoredMessagesDTO.class);
        return modelMapper;
    }
}
