package com.codestates.pre012.reply;


import com.codestates.pre012.reply.dto.ReplyDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReplyMapper {

    Reply ReplyPostDtoToReply(ReplyDto.Post replyDto);
    Reply ReplyPatchDtoToReply(ReplyDto.Patch replyDto);
    default ReplyDto.Response ReplyToReplyResponse(Reply reply) {
        ReplyDto.Response response = ReplyDto.Response.builder()
                .replyId(reply.getReplyId())
                .postsId(reply.getPosts().getPostsId())
                .content(reply.getContent())
                .username(reply.getMember().getUsername())
                .build();

        return response;
    }

}
