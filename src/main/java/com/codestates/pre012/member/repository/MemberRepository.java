package com.codestates.pre012.member.repository;

import com.codestates.pre012.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//member repository 추가
public interface MemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findByEmail(String email);

}
