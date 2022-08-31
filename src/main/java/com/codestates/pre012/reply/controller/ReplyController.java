package com.codestates.pre012.reply.controller;


import com.codestates.pre012.config.oauth.PrincipalDetails;
import com.codestates.pre012.dto.SingleResponseDto;
import com.codestates.pre012.reply.Reply;
import com.codestates.pre012.reply.ReplyMapper;
import com.codestates.pre012.reply.ReplyService;
import com.codestates.pre012.reply.dto.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/v1/posts")
@RestController
@Validated
public class ReplyController {

    private final ReplyService replyService;
    private final ReplyMapper mapper;

    @PostMapping("/reply/{postsId}")
    public ResponseEntity createReply(@PathVariable("postsId") long postsId ,
                                      @AuthenticationPrincipal PrincipalDetails principal,
                                      @RequestBody ReplyDto.Post replyPost) {

        System.out.println("#########################################");
        Reply reply = mapper.ReplyPostDtoToReply(replyPost);

        replyService.createReply(postsId,principal.getMember(), reply);

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.ReplyToReplyResponse(reply)),HttpStatus.CREATED);
    }

    @PatchMapping("/reply/{postId}/{replyId}")
    public ResponseEntity updateEntity(@PathVariable("PostId") long postId,
                                       @AuthenticationPrincipal PrincipalDetails principal,
                                       @RequestBody ReplyDto.Patch replyPatch) {
        Reply reply = mapper.ReplyPatchDtoToReply(replyPatch);
        Reply response = replyService.updateReply(postId, principal.getMember(), reply);

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.ReplyToReplyResponse(response)), HttpStatus.OK);
    }

    @DeleteMapping("/reply/{postId}/{replyId}")
    public ResponseEntity deleteEntity(@PathVariable("postId") long postId,
                                       @AuthenticationPrincipal PrincipalDetails principal,
                                       @PathVariable("replyId") long replyId) {
        replyService.deleteReply(postId,principal.getMember(),replyId);

        return new ResponseEntity<>("댓글이 삭제되었습니다.",HttpStatus.NO_CONTENT);
    }



}
