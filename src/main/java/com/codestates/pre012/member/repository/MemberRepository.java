package com.codestates.pre012.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Member;

//member repository 추가
public interface MemberRepository extends JpaRepository<Member, Long> {

}
