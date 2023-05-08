package com.seol.HW3SpringBoard.repository;

import com.seol.HW3SpringBoard.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataJpaBoardRepository extends JpaRepository<Post, Long>, BoardRepository {

}