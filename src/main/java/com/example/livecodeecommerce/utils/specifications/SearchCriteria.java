package com.example.livecodeecommerce.utils.specifications;

import com.example.livecodeecommerce.utils.constant.Operation;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchCriteria {
    private String key;
    private Operation operator;
    private String value;
}
