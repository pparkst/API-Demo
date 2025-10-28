package com.pparkst.api.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import net.datafaker.Faker;
import com.pparkst.api.domain.Board;
import com.pparkst.api.domain.Member;
import java.time.ZoneId;

public class CreateDummyData {

    public List<Board> createBoard(Long rowCount) {
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
        }
        return boardList;
    }

    public List<Member> createMember(Long rowCount) {
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

            memberList.add(member);
        }

        return memberList;
    }
}
