package com.example.minimovie.entity;

import com.example.minimovie.dto.request.MemberRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String identity;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    // 1 = 일반관람객, 2 = 전문 평론가, 3 = 관리자
    @Column(nullable = false)
    private int role = 1;

    @Builder
    public Member(String identity, String password, String nickname, int role) {
        this.identity = identity;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }

    public void updateMember(MemberRequestDto updateMember) {
        this.password = updateMember.getPassword();
        this.nickname = updateMember.getNickname();
        this.role = updateMember.getRole();
    }
}
