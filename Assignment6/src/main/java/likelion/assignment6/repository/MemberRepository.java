package likelion.assignment6.repository;

import likelion.assignment6.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{

}
