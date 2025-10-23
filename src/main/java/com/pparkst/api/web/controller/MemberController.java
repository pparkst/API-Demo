package com.pparkst.api.web.controller;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pparkst.api.service.DummyService;
import com.pparkst.api.service.MemberService;
import com.pparkst.api.web.dto.MemberCreateRequestDto;
import com.pparkst.api.web.dto.MemberResponseDto;
import com.pparkst.api.web.dto.MemberUpdateRequestDto;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final DummyService dummyService;

    @GetMapping("/{no}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long no) {
        return ResponseEntity.ok(memberService.getMemberByNo(no));
    }

    @PostMapping()
    public ResponseEntity<MemberResponseDto> addMember(@RequestBody MemberCreateRequestDto memberCreateRequestDto) {
        MemberResponseDto memberResponseDto = memberService.addMember(memberCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberResponseDto);
    }

    @PutMapping("/{no}")
    public ResponseEntity<MemberResponseDto> editName(@PathVariable Long no, @RequestBody MemberUpdateRequestDto memberUpdateRequestDto) {
        MemberResponseDto memberResponseDto = memberService.editMember(memberUpdateRequestDto);
        return ResponseEntity.ok(memberResponseDto);
    }

    @DeleteMapping("/{no}")
    public ResponseEntity<Void> removeMember(@PathVariable Long no) {
        memberService.removeMember(no);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dummy/{rowCount}")
    public ResponseEntity<Void> getMethodName(@PathVariable Long rowCount) {
        dummyService.createMemberDummyData(rowCount);
        return ResponseEntity.noContent().build();
    }
    
    
}
