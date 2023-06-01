package com.example.orderhw.Repository;

import com.example.orderhw.Domain.CancelLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CancelRepository extends JpaRepository<CancelLog, Long> {
}
