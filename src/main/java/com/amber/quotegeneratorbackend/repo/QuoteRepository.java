package com.amber.quotegeneratorbackend.repo;

import com.amber.quotegeneratorbackend.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
