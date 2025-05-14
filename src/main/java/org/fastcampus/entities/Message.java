package org.fastcampus.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Message {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    @Id
    private Long id;
    private String text;
    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;
    @JoinColumn(name = "chatroom_id")
    @ManyToOne
    private Chatroom chatroom;
}
