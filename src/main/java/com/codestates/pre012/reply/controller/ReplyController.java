package com.codestates.pre012.reply.controller;

import com.codestates.pre012.config.oauth.PrincipalDetails;
import com.codestates.pre012.reply.dto.ReplyDto;
import com.codestates.pre012.reply.entity.Reply;
import com.codestates.pre012.reply.mapper.ReplyMapper;
import com.codestates.pre012.reply.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/reply")
public class ReplyController {

    private final ReplyService replyService;

    private final ReplyMapper mapper;

    public ReplyController(ReplyService replyService, ReplyMapper mapper) {
        this.replyService = replyService;
        this.mapper = mapper;
    }


    // 댓글 생성
    @PostMapping("/reply/{postId}")
    public ResponseEntity createReply(@PathVariable("postId") Long postId,
                                      @RequestBody ReplyDto.Post replyDtoPost,
                                      @AuthenticationPrincipal PrincipalDetails principal) {
        Reply reply = mapper.ReplyDtoPostToReply(replyDtoPost);
        Reply newReply = replyService.createReply(postId, principal.getMember(), reply);
        return new ResponseEntity<>(newReply, HttpStatus.CREATED);
    }

    // 댓글 수정 (본인이 작성한 글을 수정해야 한다.)
    @PatchMapping("/update/{reply-id}")
    public ResponseEntity updatedReply(@PathVariable("reply-id") Long id, @RequestBody ReplyDto.Patch replyDtoPatch) {
    // jwt --> 추출 맞으면 ok

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 댓글 삭제 (본인이 작성한 글을 삭제해야 한다.)
    @DeleteMapping("/{reply-id}")
    public ResponseEntity deleteReply(@PathVariable("reply-id") Long id) {


        return new ResponseEntity<>(HttpStatus.OK);
    }
}
