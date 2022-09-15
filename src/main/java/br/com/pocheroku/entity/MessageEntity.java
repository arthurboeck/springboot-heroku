package br.com.pocheroku.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "SPRINGBOOT_MESSAGE")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MESSAGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @NotEmpty
    @JsonProperty("message")
    @Column(name = "MESSAGE_DESCR")
    private String messageDescription;

    @Column(name = "MESSAGE_DATE")
    private LocalDateTime messageDate;
}

//    create table springboot_message (
//        message_id  bigserial not null,
//        message_date timestamp,
//        message_descr varchar(255),
//    primary key (message_id)
//    )