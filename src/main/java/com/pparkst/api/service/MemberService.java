package com.pparkst.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pparkst.api.mapper.MemberMapper;
import com.pparkst.api.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final static Logger logger = LoggerFactory.getLogger(MemberService.class);

    
}
