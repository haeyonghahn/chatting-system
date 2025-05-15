package org.fastcampus.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Role {
    USER("ROLE_USER"), CONSULTANT("ROLE_CONSULTANT");

    private final String code;

    Role(String code) {
        this.code = code;
    }

    public static Role fromCode(String code) {
        return Arrays.stream(
                Role.values())
                .filter(role -> role.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid role code: " + code));
    }
}
