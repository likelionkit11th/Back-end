package com.likelion.hw5.repository;

import com.likelion.hw5.domain.CancelHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CancelHistoryRepository extends CrudRepository<CancelHistory, Long> {

}
