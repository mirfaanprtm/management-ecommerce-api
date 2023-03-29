package com.example.livecodeecommerce.models.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class SearchData {
    private String searchKey;
    private Integer searchOther;
    private LocalDate searchDate;
    private Long search;

}
