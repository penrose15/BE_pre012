package com.codestates.pre012.member.mapper;

import com.codestates.pre012.member.dto.PostMemberDto;
import com.codestates.pre012.member.dto.ResponseMemberDto;
import com.codestates.pre012.member.entity.Member;
import org.mapstruct.Mapper;
import org.springframework.http.ResponseEntity;

//mapper어노테이션 추가
@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member PostMemberDtoToMember(PostMemberDto postMemberDto);

    ResponseMemberDto MemberToResponseMemberDto(Member member);
}
