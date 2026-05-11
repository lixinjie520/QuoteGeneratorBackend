package com.amber.quotegeneratorbackend.service;

import com.amber.quotegeneratorbackend.entity.Quote;
import com.amber.quotegeneratorbackend.exception.ResourceNotFoundException;
import com.amber.quotegeneratorbackend.repo.QuoteRepository;
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

    public Quote getQuoteById(Long id) {
      return quoteRepository.findById(id)
              .orElseThrow(()->new ResourceNotFoundException("Quote not found with id: " + id));
    }

    public Quote addQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    public Quote updateQuote(Long id, Quote quote) {

        Quote existingQuote = quoteRepository.findById(id)
                .orElseThrow(()->
                    new ResourceNotFoundException("Quote not found with id: " + id)
                );

        existingQuote.setContent(quote.getContent());
        existingQuote.setAuthor(quote.getAuthor());
        existingQuote.setCategory(quote.getCategory());

        return quoteRepository.save(existingQuote);

    }

    public void deleteQuote(Long id) {
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Quote not found with id: " + id));

        quoteRepository.delete(quote);
    }

    public List<Quote> searchQuotes(String keyword) {
        return quoteRepository.searchByKeyword(keyword);
    }

    public List<Quote> getQuotesByCategory(String category) {
        return quoteRepository.findQuotesByCategoryIgnoreCase(category);
    }
}
