package com.codestates.pre012.member.controller;

import com.codestates.pre012.dto.SingleResponseDto;
import com.codestates.pre012.member.dto.MemberDto;
import com.codestates.pre012.member.entity.Member;
import com.codestates.pre012.member.mapper.MemberMapper;
import com.codestates.pre012.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
@Validated
public class RestApiController {

    private final MemberService memberService;
    private final MemberMapper mapper;

    // 추가
    @PostMapping("/create")
    public ResponseEntity join(@Validated @RequestBody MemberDto.Post postMember) {

        Member member = mapper.memberPostDtoToMember(postMember);
        memberService.saveMember(member);
        return new ResponseEntity<>("회원 가입 완료", HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity user() {
        return new ResponseEntity<>("user 권한을 가지고 있습니다.", HttpStatus.OK);
    }


    @GetMapping("/manager")
    public ResponseEntity manager() {
        return new ResponseEntity<>("manager 권한을 가지고 있습니다.", HttpStatus.OK);
    }


    @GetMapping("/admin")
    public ResponseEntity admin() {
        return new ResponseEntity<>("admin 권한을 가지고 있습니다. ", HttpStatus.OK);
    }


    @GetMapping("/login")
    public ResponseEntity login() {

        return new ResponseEntity<>("토큰이 존재하지 않습니다. ", HttpStatus.OK);
    }


    @GetMapping("/{member-id}")
    public ResponseEntity findMember(@PathVariable("member-id") long memberId) {

        Member findMember = memberService.lookMember(memberId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.memberToMemberResponseDto(findMember))
                , HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public void deleteMember(@PathVariable("member-id") long memberId) {

        memberService.deleteMember(memberId);
    }
}
