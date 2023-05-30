package com.likelion.hw5.repository;


import com.likelion.hw5.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
