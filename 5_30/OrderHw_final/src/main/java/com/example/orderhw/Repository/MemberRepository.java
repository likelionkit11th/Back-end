package com.example.orderhw.Repository;

import com.example.orderhw.Domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Page<Member> findAll(Pageable pageable);
}
