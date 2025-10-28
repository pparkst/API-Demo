package com.pparkst.api.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pparkst.api.mapper.MemberMapper;
import com.pparkst.api.repository.MemberRepository;
import com.pparkst.api.util.CreateDummyData;
import com.pparkst.api.web.dto.MemberCreateRequestDto;
import com.pparkst.api.web.dto.MemberResponseDto;
import com.pparkst.api.web.dto.MemberUpdateRequestDto;
import com.pparkst.api.web.dto.ResponseMessageDto;

import jakarta.persistence.EntityManager;

import com.pparkst.api.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final static Logger logger = LoggerFactory.getLogger(MemberService.class);
    private final EntityManager entityManager;
    private final BatchProcessingService batchProcessingService;

    public MemberResponseDto getMemberByNo(Long no) {
        logger.info("getMemberByNo");
        logger.debug("getMemberByNo no = " + no);

        Member member = memberRepository.findById(no).orElseThrow(() -> new RuntimeException("해당하는 고객이 없습니다."));
        MemberResponseDto memberResponseDto = memberMapper.toResponseDto(member);
        return memberResponseDto;
    }

    public MemberResponseDto addMember(MemberCreateRequestDto memberCreateRequestDto) {
        logger.info("addMember");
        logger.debug("addMember name = " + memberCreateRequestDto.name + " account = " + memberCreateRequestDto.account + " password = " + memberCreateRequestDto.password);

        Member member = memberMapper.toEntity(memberCreateRequestDto);
        member.softCreate();
        return memberMapper.toResponseDto(memberRepository.save(member));
    }

    @Transactional
    public MemberResponseDto editMember(MemberUpdateRequestDto memberUpdateRequestDto) {
        logger.info("editMember");
        logger.debug("editMember no = " + memberUpdateRequestDto.no +" name = " + memberUpdateRequestDto.name + " password = " + memberUpdateRequestDto.password);

        Member memberOrigin = memberRepository.findById(memberUpdateRequestDto.no).orElseThrow(() -> new RuntimeException("해당하는 고객이 없습니다."));

        if(memberUpdateRequestDto.password == null || memberUpdateRequestDto.password.isBlank()) {
            if(memberUpdateRequestDto.name != null && !memberUpdateRequestDto.password.isBlank()) {
                memberOrigin.softUpdateName(memberUpdateRequestDto.name);
                return memberMapper.toResponseDto(memberOrigin);
            }
        }else{
            if(memberUpdateRequestDto.name == null || memberUpdateRequestDto.name.isBlank()) {
                memberOrigin.softUpdate(memberUpdateRequestDto.password);
                return memberMapper.toResponseDto(memberOrigin);
            }
        }

        memberOrigin.softUpdate(memberUpdateRequestDto.password, memberUpdateRequestDto.name);
        return memberMapper.toResponseDto(memberOrigin);
    }

    @Transactional
    public void removeMember(Long no) {
        logger.info("removeMember");
        logger.debug("removeMember no = " + no);

        Member memberOrigin = memberRepository.findById(no).orElseThrow(() -> new RuntimeException("해당하는 고객이 없습니다."));
        memberOrigin.softDelete();
    }

    public ResponseMessageDto insertDummyDataBatchProcessing(Long rowCount) {
        ResponseMessageDto responseMessageDto = new ResponseMessageDto();

        CreateDummyData createDummyData = new CreateDummyData();
        List<Member> memberDummyList = createDummyData.createMember(rowCount);

        try {
            batchProcessingService.processBulkData(memberDummyList, memberRepository);
        } catch (Exception e) {
            responseMessageDto.setMessage(e.getMessage());
            logger.error("Error during batch processing", e);
            throw new RuntimeException("Batch processing failed", e);
        }

        return responseMessageDto;
    }

    
}
