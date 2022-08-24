package com.codestates.pre012.member.service;


import com.codestates.pre012.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

//memberservice 추가, repository 의존성 주입
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}
