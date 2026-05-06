package com.amber.quotegeneratorbackend.repo;

import com.amber.quotegeneratorbackend.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Query("""
            SELECT q FROM Quote q
            WHERE LOWER(q.content) LIKE LOWER(CONCAT('%', :keyword, '%'))
              OR LOWER(q.author) LIKE LOWER(CONCAT('%', :keyword, '%'))
              OR LOWER(q.category) LIKE LOWER(CONCAT('%', :keyword, '%'))
  """)
    List<Quote> searchByKeyword(@Param("keyword") String keyword);

    List<Quote> findQuotesByCategoryIgnoreCase(@Param("category") String category);
}
