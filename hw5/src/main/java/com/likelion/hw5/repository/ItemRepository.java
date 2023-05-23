package com.likelion.hw5.repository;


import com.likelion.hw5.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Long, Item> {
}
