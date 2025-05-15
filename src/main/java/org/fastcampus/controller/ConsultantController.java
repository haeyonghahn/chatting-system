package org.fastcampus.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.dtos.ChatroomDto;
import org.fastcampus.dtos.MemberDto;
import org.fastcampus.services.ConsultantService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/consultants")
@Controller
public class ConsultantController {

    private final ConsultantService consultantService;

    @ResponseBody
    @PostMapping
    public MemberDto saveMember(@RequestBody MemberDto memberDto) {
        log.info("ConsultantController.saveMember() called with {}", memberDto);
        return consultantService.saveMember(memberDto);
    }

    @GetMapping
    public String index() {
        return "consultants/index.html";
    }

    @ResponseBody
    @GetMapping("/chats")
    public List<ChatroomDto> getAllChatrooms() {
        return consultantService.getAllChatrooms();
    }
}
