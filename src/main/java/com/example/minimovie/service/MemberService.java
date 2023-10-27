package com.example.minimovie.service;

import com.example.minimovie.dto.request.MemberRequestDto;
import com.example.minimovie.dto.response.MemberResponseDto;
import com.example.minimovie.entity.Member;
import com.example.minimovie.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void createMember(MemberRequestDto createDto) {
        isIdentity(createDto.getIdentity());

        memberRepository.save(toEntity(createDto));
    }

    public MemberResponseDto loginMember(String memberIdentity, String memberPassword) {
        Optional<Member> member = memberRepository.findMemberByIdentity(memberIdentity);

        isMember(member);

        isPasswordMatch(member, memberPassword);

        return toDto(member.get());
    }

    public MemberResponseDto readMemberDetail(String memberIdentity) {
        return toDto(memberRepository.findMemberByIdentity(memberIdentity).get());
    }

    public List<MemberResponseDto> readAllMembers() {
        return memberRepository.findAll().stream().map(this::toDto).toList();
    }

    public void updateMember(MemberRequestDto updateDto) {
        Optional<Member> member = memberRepository.findMemberByIdentity(updateDto.getIdentity());

        isMember(member);

        member.get().updateMember(updateDto);

        memberRepository.save(member.get());
    }

    public void deleteMember(String memberIdentity) {
        Optional<Member> member = memberRepository.findMemberByIdentity(memberIdentity);

        isMember(member);

        memberRepository.delete(member.get());
    }

    // 해당 멤버 존재 체크
    private void isMember(Optional<Member> member) {
        if (member.isEmpty()) {
            throw new RuntimeException();
        }
    }

    // 회원의 identity 값 중복 체크
    private void isIdentity(String identity) {
        if (memberRepository.findMemberByIdentity(identity).isPresent()) {
            throw new RuntimeException();
        }
    }

    private void isPasswordMatch(Optional<Member> loginMember, String password) {
        if (!loginMember.get().getPassword().equals(password)) {
            throw new RuntimeException();
        }
    }

    private Member toEntity(MemberRequestDto dto) {
        return Member.builder()
                .identity(dto.getIdentity())
                .password(dto.getPassword())
                .nickname(dto.getNickname())
                .role(1)
                .build();
    }

    private MemberResponseDto toDto(Member member) {
        return MemberResponseDto.builder()
                .identity(member.getIdentity())
                .nickname(member.getNickname())
                .role(member.getRole())
                .build();
    }

}
