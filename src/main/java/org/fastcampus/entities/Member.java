package org.fastcampus.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.enums.Gender;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    @Id
    private Long id;
    private String email;
    private String nickname;
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String phoneNumber;
    private LocalDate birthDay;
    private String role;

    public void updatePassword(String password, String confirmPassword, PasswordEncoder passwordEncoder) {
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        this.password = passwordEncoder.encode(password);
    }
}
