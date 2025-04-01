package com.expense.mgmt.domain.model.dto.news;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Article {

    private String title;

    private String description;

    private String url;

    private String urlToImage;

    private String publishedAt;

}
