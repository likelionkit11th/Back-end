package com.HW6OrderService.Repository;

import com.HW6OrderService.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
