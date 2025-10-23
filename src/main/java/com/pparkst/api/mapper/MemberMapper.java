package com.pparkst.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pparkst.api.domain.Member;
import com.pparkst.api.web.dto.MemberCreateRequestDto;
import com.pparkst.api.web.dto.MemberResponseDto;
import com.pparkst.api.web.dto.MemberUpdateRequestDto;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mapping(target = "no", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "withdrawalAt", ignore = true)
    @Mapping(target = "isWithdrawal", constant = "false")
    Member toEntity(MemberCreateRequestDto dto);

    @Mapping(target = "account", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isWithdrawal", ignore = true)
    @Mapping(target = "withdrawalAt", ignore = true)
    Member toEntity(MemberUpdateRequestDto dto);

    MemberResponseDto toResponseDto(Member member);

}
