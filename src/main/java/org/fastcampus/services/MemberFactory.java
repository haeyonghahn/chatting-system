package org.fastcampus.services;

import org.fastcampus.entities.Member;
import org.fastcampus.enums.Gender;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class MemberFactory {

    public static Member member(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        return switch (userRequest.getClientRegistration().getRegistrationId()) {
            case "kakao" -> {
                Map<String, Object> attributeMap = oAuth2User.getAttribute("kakao_account");
                yield Member.builder()
                        .email((String) attributeMap.get("email"))
                        .nickname((String) ((Map<?, ?>) attributeMap.get("profile")).get("nickname"))
                        .name((String) attributeMap.get("name"))
                        .phoneNumber((String) attributeMap.get("phone_number"))
                        .gender(Gender.valueOf(((String) attributeMap.get("gender")).toUpperCase()))
                        .birthDay(getBirthDay(attributeMap))
                        .role("USER_ROLE")
                        .build();
            }
            case "google" -> {}
            default -> throw new IllegalArgumentException("연동되지 않은 서비스입니다.");
        }
    }
}
