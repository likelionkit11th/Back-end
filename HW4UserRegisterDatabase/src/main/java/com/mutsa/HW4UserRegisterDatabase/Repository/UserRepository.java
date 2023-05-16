package com.mutsa.HW4UserRegisterDatabase.Repository;

import com.mutsa.HW4UserRegisterDatabase.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
