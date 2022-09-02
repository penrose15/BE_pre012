package com.codestates.pre012.member.repository;


import com.codestates.pre012.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findByUsername(String member);

    Optional<Member> findByEmail(String email);
}