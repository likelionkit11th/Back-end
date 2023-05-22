package com.mutsa.homework5.repository;

import com.mutsa.homework5.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
