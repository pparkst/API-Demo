package com.pparkst.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pparkst.api.domain.Member;
import com.pparkst.api.web.dto.MemberCreateRequestDto;
import com.pparkst.api.web.dto.MemberResponseDto;
import com.pparkst.api.web.dto.MemberUpdateRequestDto;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mapping(target = "no", ignore = false)
    @Mapping(target = "createdAt", ignore = false)
    @Mapping(target = "updateAt", ignore = false)
    @Mapping(target = "withdrawalAt", ignore = false)
    @Mapping(target = "isWithdrawal", constant = "false")
    Member toEntity(MemberCreateRequestDto dto);

    @Mapping(target = "account", ignore = false)
    @Mapping(target = "createdAt", ignore = false)
    @Mapping(target = "updateAt", ignore = false)
    @Mapping(target = "isWithdrawal", ignore = false)
    @Mapping(target = "withdrawalAt", ignore = false)
    Member toEntity(MemberUpdateRequestDto dto);

    @Mapping(target = "password")
    @Mapping(target = "createdAt", ignore = false)
    @Mapping(target = "updateAt", ignore = false)
    @Mapping(target = "isWithdrawal", ignore = false)
    @Mapping(target = "withdrawalAt", ignore = false)
    MemberResponseDto toResponseDto(Member member);

}
