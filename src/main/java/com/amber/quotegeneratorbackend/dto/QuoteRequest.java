package com.amber.quotegeneratorbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteRequest {

    private String content;
    private String author;
    private String category;
}
