package org.fastcampus.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.dtos.ChatroomDto;
import org.fastcampus.dtos.MemberDto;
import org.fastcampus.entities.Chatroom;
import org.fastcampus.entities.Member;
import org.fastcampus.enums.Role;
import org.fastcampus.repository.ChatroomRepository;
import org.fastcampus.repository.MemberRepository;
import org.fastcampus.vos.CustomUserDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConsultantService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final ChatroomRepository chatroomRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByName(username).get();
        if (Role.fromCode(member.getRole()) != Role.CONSULTANT) {
            throw new AccessDeniedException("상담사가 아닙니다.");
        }
        return new CustomUserDetail(member);
    }

    public MemberDto saveMember(MemberDto memberDto) {
        Member member = MemberDto.to(memberDto);
        member.updatePassword(memberDto.password(), memberDto.confirmPassword(), passwordEncoder);

        member = memberRepository.save(member);

        return MemberDto.from(member);
    }

    public List<ChatroomDto> getAllChatrooms() {
        List<Chatroom> chatroomList = chatroomRepository.findAll();
        return chatroomList.stream()
                .map(ChatroomDto::from)
                .toList();
    }
}
