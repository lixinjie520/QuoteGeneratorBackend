package com.amber.quotegeneratorbackend.controller;

import com.amber.quotegeneratorbackend.entity.Quote;
import com.amber.quotegeneratorbackend.service.QuoteService;
import jakarta.servlet.ServletRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quotes")
@CrossOrigin(origins = "http:localhost:5173")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping
    public ResponseEntity<List<Quote>> getAllQuotes(){
        List<Quote> quotes = quoteService.getAllQuotes();

        if(quotes.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(quotes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quote> getQuoteById(@PathVariable Long id){
        return quoteService.getQuoteById(id) //Optional<Quote>
                .map(ResponseEntity::ok) //.map(quote -> ResponseEntity.ok(quote))
                .orElseGet(()->ResponseEntity.notFound().build()); //404 Not Found
    }

    @PostMapping
    public ResponseEntity<Quote> addQuote(@RequestBody Quote quote){
        Quote savedQuote = quoteService.addQuote(quote);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quote> updateQuote(@PathVariable Long id, @RequestBody Quote quote){
        return quoteService.updateQuote(id, quote)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id){
        boolean deleted = quoteService.deleteQuote(id);

        if(deleted){
            return ResponseEntity.noContent().build();// 204
        }else{
            return ResponseEntity.notFound().build();// 404
        }

    }

    @GetMapping("/search")
    public ResponseEntity<List<Quote>> searchQuotes(@RequestParam String keyword){
        List<Quote> quotes = quoteService.searchQuotes(keyword);

        if(quotes.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(quotes);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Quote>> getQuotesByCategory(@PathVariable String category){
        List<Quote> quotes = quoteService.getQuotesByCategory(category);

        if(quotes.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(quotes);
    }

}
