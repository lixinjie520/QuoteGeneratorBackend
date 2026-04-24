package com.amber.quotegeneratorbackend.service;

import aj.org.objectweb.asm.commons.Remapper;
import com.amber.quotegeneratorbackend.entity.Quote;
import com.amber.quotegeneratorbackend.repo.QuoteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    public Optional<Quote> getQuoteById(Long id) {
      return quoteRepository.findById(id);
    }

    public Quote addQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    public Optional<Quote> updateQuote(Long id, Quote quote) {
        return quoteRepository.findById(id)// 先檢查該id是否存在
                .map(exitingQuote ->{
                    exitingQuote.setContent(quote.getContent());
                    exitingQuote.setAuthor(quote.getAuthor());
                    exitingQuote.setCategory(quote.getCategory());

                    return quoteRepository.save(exitingQuote);
                });
    }

    public boolean deleteQuote(Long id) {
        if(!quoteRepository.existsById(id)){
            return false;
        }
        quoteRepository.deleteById(id);
        return true;
    }
}
