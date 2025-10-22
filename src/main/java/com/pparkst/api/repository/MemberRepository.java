package com.pparkst.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pparkst.api.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    public abstract Optional<Member> findByNoAndIsWithdrawalFalse(Long no);
}
