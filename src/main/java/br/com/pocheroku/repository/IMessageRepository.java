package br.com.pocheroku.repository;

import br.com.pocheroku.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IMessageRepository extends JpaRepository<MessageEntity, Long>, JpaSpecificationExecutor<MessageEntity> {

    List<MessageEntity> findByMessageDateGreaterThanEqual(LocalDateTime dateTime);

    List<MessageEntity> findAll();
}
