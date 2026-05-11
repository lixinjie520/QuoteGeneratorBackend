package com.amber.quotegeneratorbackend.mapper;

import com.amber.quotegeneratorbackend.dto.QuoteRequest;
import com.amber.quotegeneratorbackend.dto.QuoteResponse;
import com.amber.quotegeneratorbackend.entity.Quote;

public class QuoteMapper {

    public static Quote toEntity(QuoteRequest request){
        Quote quote = new Quote();
        quote.setContent(request.getContent());
        quote.setAuthor(request.getAuthor());
        quote.setCategory(request.getCategory());

        return quote;
    }
    public static QuoteResponse toResponse(Quote quote){
        QuoteResponse res = new QuoteResponse();
        res.setId(quote.getId());
        res.setContent(quote.getContent());
        res.setAuthor(quote.getAuthor());
        res.setCategory(quote.getCategory());
        res.setCreatedAt(quote.getCreatedAt());
        res.setUpdatedAt(quote.getUpdatedAt());

        return res;
    }
}
