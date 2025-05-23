package org.fastcampus.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Chatroom {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    @Id
    private Long id;
    private String title;
    @OneToMany(mappedBy = "chatroom")
    private Set<MemberChatroomMapping> memberChatroomMappingSet;
    @Setter
    @Transient
    private Boolean hasNewMessage;
    private LocalDateTime createdAt;

    public MemberChatroomMapping addMember(Member member) {
        if (memberChatroomMappingSet == null) {
            this.memberChatroomMappingSet = new HashSet<>();
        }

        MemberChatroomMapping memberChatroomMapping = MemberChatroomMapping.builder()
                .member(member)
                .chatroom(this)
                .build();

        this.memberChatroomMappingSet.add(memberChatroomMapping);

        return memberChatroomMapping;
    }
}
