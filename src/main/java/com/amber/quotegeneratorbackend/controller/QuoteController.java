package com.amber.quotegeneratorbackend.controller;

import com.amber.quotegeneratorbackend.dto.QuoteRequest;
import com.amber.quotegeneratorbackend.dto.QuoteResponse;
import com.amber.quotegeneratorbackend.entity.Quote;
import com.amber.quotegeneratorbackend.mapper.QuoteMapper;
import com.amber.quotegeneratorbackend.service.QuoteService;
import jakarta.servlet.ServletRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quotes")
@CrossOrigin(origins = "http://localhost:5173")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping
    public ResponseEntity<List<QuoteResponse>> getAllQuotes(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String keyword
    ){
        List<Quote> quotes;

        if(category != null && !category.isBlank()){
            quotes = quoteService.getQuotesByCategory(category);
        } else if(keyword != null && !keyword.isBlank()){
            quotes = quoteService.searchQuotes(keyword);
        } else {
            quotes = quoteService.getAllQuotes();
        }

        if(quotes.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<QuoteResponse> response = quotes.stream()
                .map(QuoteMapper::toResponse)// q -> QuoteMapper.toResponse(q)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuoteResponse> getQuoteById(@PathVariable Long id){
        Quote quote = quoteService.getQuoteById(id);
        return ResponseEntity.ok(QuoteMapper.toResponse(quote));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<QuoteResponse>> getQuotesByCategory(@PathVariable String category){
        List<Quote> quotes = quoteService.getQuotesByCategory(category);

        if (quotes.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<QuoteResponse> response = quotes.stream()
                .map(QuoteMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<QuoteResponse> addQuote(@RequestBody QuoteRequest request){

        Quote quote = QuoteMapper.toEntity(request);

        Quote saved = quoteService.addQuote(quote);

        QuoteResponse res = QuoteMapper.toResponse(saved);

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuoteResponse> updateQuote(
            @PathVariable Long id,
            @RequestBody QuoteRequest request){

        Quote quote = QuoteMapper.toEntity(request);

        Quote updated = quoteService.updateQuote(id, quote);

        return ResponseEntity.ok(QuoteMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id){
        quoteService.deleteQuote(id);

        return ResponseEntity.noContent().build();
    }

}
