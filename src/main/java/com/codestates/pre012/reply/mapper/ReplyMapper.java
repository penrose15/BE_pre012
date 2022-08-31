package com.codestates.pre012.reply.mapper;

import com.codestates.pre012.reply.dto.ReplyDto;
import com.codestates.pre012.reply.entity.Reply;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReplyMapper {
    Reply ReplyDtoPostToReply(ReplyDto.Post requestBody);

    Reply ReplyDtoPatchToReply(ReplyDto.Patch requestBody);

    ReplyDto.Response ReplyToReplyDtoResponse(Reply reply);
}
