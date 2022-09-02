package com.codestates.pre012.reply.mapper;


import com.codestates.pre012.reply.entity.Reply;
import com.codestates.pre012.reply.dto.ReplyDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReplyMapper {

    Reply ReplyPostDtoToReply(ReplyDto.Post replyDto);
    Reply ReplyPatchDtoToReply(ReplyDto.Patch replyDto);
    ReplyDto.Response ReplyToReplyDtoResponse(Reply reply);

}
