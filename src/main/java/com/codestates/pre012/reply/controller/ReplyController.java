package com.codestates.pre012.reply.controller;

import com.codestates.pre012.config.oauth.PrincipalDetails;
import com.codestates.pre012.dto.SingleResponseDto;
import com.codestates.pre012.reply.entity.Reply;
import com.codestates.pre012.reply.mapper.ReplyMapper;
import com.codestates.pre012.reply.service.ReplyService;
import com.codestates.pre012.reply.dto.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/v1/reply")
@RestController
@Validated
public class ReplyController {

    private final ReplyService replyService;
    private final ReplyMapper mapper;

    @PostMapping("/{postsId}")
    public ResponseEntity createReply(@PathVariable("postsId") long postsId,
                                      @AuthenticationPrincipal PrincipalDetails principal,
                                      @RequestBody ReplyDto.Post replyPost) {

        Reply reply = mapper.ReplyPostDtoToReply(replyPost);
        replyService.createReply(postsId, principal.getMember(), reply);

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.ReplyToReplyDtoResponse(reply)), HttpStatus.CREATED);
    }

    @PatchMapping("/{replyId}")
    public ResponseEntity updateEntity(@PathVariable("replyId") long replyId,
                                       @AuthenticationPrincipal PrincipalDetails principal,
                                       @RequestBody ReplyDto.Patch replyPatch) {

        Reply reply = mapper.ReplyPatchDtoToReply(replyPatch);
        reply.setReplyId(replyId);
        Reply response = replyService.updateReply(principal.getMember(), reply);

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.ReplyToReplyDtoResponse(response)), HttpStatus.OK);
    }

    @DeleteMapping("/{replyId}")
    public ResponseEntity deleteEntity(@PathVariable("replyId") long replyId,
                                       @AuthenticationPrincipal PrincipalDetails principal) {

        replyService.deleteReply(principal.getMember(), replyId);

        return new ResponseEntity<>("댓글이 삭제되었습니다.", HttpStatus.NO_CONTENT);
    }


}