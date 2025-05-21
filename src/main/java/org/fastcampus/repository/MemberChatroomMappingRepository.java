package org.fastcampus.repository;

import org.fastcampus.entities.MemberChatroomMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberChatroomMappingRepository extends JpaRepository<MemberChatroomMapping, Long> {

    Boolean existsByMemberIdAndChatroomId(Long memberId, Long memberChatroomId);
    void deleteByMemberIdAndChatroomId(Long memberId, Long memberChatroomId);
    List<MemberChatroomMapping> findAllByMemberId(Long memberId);
    Optional<MemberChatroomMapping> findByMemberIdAndChatroomId(Long memberId, Long chatroomId);
}
