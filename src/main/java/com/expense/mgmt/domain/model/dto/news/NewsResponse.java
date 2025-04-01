package com.expense.mgmt.domain.model.dto.news;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class NewsResponse {

    private String status;

    private int totalResults;

    private List<Article> articles;
}
