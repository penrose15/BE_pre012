package com.codestates.pre012.reply;


import com.codestates.pre012.reply.dto.ReplyDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReplyMapper {

    Reply ReplyPostDtoToReply(ReplyDto.Post replyDto);
    Reply ReplyPatchDtoToReply(ReplyDto.Patch replyDto);
    ReplyDto.Response ReplyToReplyResponse(Reply reply);

}
