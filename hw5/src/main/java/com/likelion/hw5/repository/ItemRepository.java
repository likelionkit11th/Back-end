package com.likelion.hw5.repository;


import com.likelion.hw5.domain.Item;
import com.likelion.hw5.domain.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
}
