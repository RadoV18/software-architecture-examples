package com.radov18.borrowing.repository;

import com.radov18.borrowing.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
}
