package com.seoljy.HW41SpringBoard.repository;

import com.seoljy.HW41SpringBoard.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataJpaBoardRepository extends JpaRepository<Post, Long>, BoardRepository {

}