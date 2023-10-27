package com.example.minimovie.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    private String identity;

    private String password;

    private String nickname;

    private int role;

}
