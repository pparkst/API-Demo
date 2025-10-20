package com.pparkst.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pparkst.api.domain.Board;
import com.pparkst.api.web.dto.BoardCreateRequestDto;
import com.pparkst.api.web.dto.BoardResponseDto;
import com.pparkst.api.web.dto.BoardUpdateRequestDto;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "isDeleted", constant = "false")
    Board toEntity(BoardCreateRequestDto dto);
    
    BoardCreateRequestDto toCreateRequestDto(Board entity);

    List<Board> toEntityList(List<BoardCreateRequestDto> dtoList);


    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "member_No", ignore = true)
    Board toEntity(BoardUpdateRequestDto dto);


    BoardResponseDto toResponseDto(Board entity);
}
