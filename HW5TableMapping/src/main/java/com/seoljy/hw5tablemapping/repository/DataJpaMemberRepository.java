package com.seoljy.hw5tablemapping.repository;

import com.seoljy.hw5tablemapping.domain.Member;
import com.seoljy.hw5tablemapping.domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataJpaMemberRepository extends JpaRepository<Member, Long> {

}
