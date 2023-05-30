package com.seoljy.hw6orderservice.repository;

import com.seoljy.hw6orderservice.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataJpaMemberRepository extends JpaRepository<Member, Long> {

}
