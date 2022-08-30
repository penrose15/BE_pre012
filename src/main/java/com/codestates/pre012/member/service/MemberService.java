package com.codestates.pre012.member.service;


import com.codestates.pre012.member.entity.Member;
import com.codestates.pre012.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Member saveMember(Member member) {

        verifiedMemberEmail(member.getEmail());
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        member.setRoles("ROLE_USER");

        return memberRepository.save(member);
    }

    public Member lookMember(long memberId) {

        return findVerifiedMember(memberId);
    }


    public Page<Member> findAllMembers(int page, int size) {
        return memberRepository.findAll(PageRequest.of(page, size,
                Sort.by("memberId").descending()));
    }


    public void deleteMember(long memberId) {
        Member findMember = findVerifiedMember(memberId);
        memberRepository.delete(findMember);
    }



    //이메일 존재시 예외처리
    public void verifiedMemberEmail(String email) {
        Optional<Member> verifyMember = memberRepository.findByEmail(email);
        if(verifyMember.isPresent()) throw new RuntimeException("member Already Exist");
    }

    public Member loginVerifiedEmail(String email) {
        Optional<Member> loginMember = memberRepository.findByEmail(email);
        Member member = loginMember.orElseThrow(() -> new RuntimeException("email not exist"));

        return member;
    }

    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new RuntimeException("memberId not exist"));
        return findMember;
    }
}