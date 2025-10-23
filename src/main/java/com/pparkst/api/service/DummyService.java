package com.pparkst.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.datafaker.Faker;
import com.pparkst.api.domain.Board;
import com.pparkst.api.domain.Member;
import com.pparkst.api.repository.BoardRepository;
import com.pparkst.api.repository.MemberRepository;

import jakarta.persistence.EntityManager;

import java.time.ZoneId;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class DummyService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final EntityManager entityManager;

    @Transactional
    public void createBoardDummyData(Long rowCount) {
        Faker faker = new Faker(new Locale("ko", "KR"));
        Random random = new Random();

        List<Board> boardList = new ArrayList<Board>();
        for (int i = 1; i < rowCount+1; i++) {

            Board board = new Board(
                null, 
                faker.book().title(), 
                faker.lorem().paragraph(5), 
                (long)random.nextInt(1000000000), 
                false, 
                faker.timeAndDate().past(5000, TimeUnit.DAYS).atZone(ZoneId.systemDefault()).toLocalDateTime(), 
                null, 
                null
            );

            boardList.add(board);

            if(i%50 == 0) {
                boardRepository.saveAll(boardList);
                boardList = new ArrayList<Board>();
                entityManager.flush();
                entityManager.clear();
            }
        }

        boardRepository.saveAll(boardList);
    }

    @Transactional
    public void createMemberDummyData(Long rowCount) {
        Faker faker = new Faker(new Locale("ko", "KR"));
        Faker enFaker = new Faker(new Locale("en", "US"));
        List<Member> memberList = new ArrayList<Member>();

        for (int i = 1; i < rowCount+1; i++) {

            Member member = new Member(
                null,
                enFaker.name().firstName().toLowerCase() + enFaker.number().digits(3),
                faker.lorem().characters(8, 20, true, true),
                faker.name().lastName() + faker.name().firstName(),
                faker.timeAndDate().past(5000, TimeUnit.DAYS).atZone(ZoneId.systemDefault()).toLocalDateTime(),
                null,
                false,
                null
            );

            System.out.println(member);

            memberList.add(member);

            if(i%50 == 0) {
                memberRepository.saveAll(memberList);
                memberList = new ArrayList<Member>();
                entityManager.flush();
                entityManager.clear();
            }
        }

        memberRepository.saveAll(memberList);
    }
}