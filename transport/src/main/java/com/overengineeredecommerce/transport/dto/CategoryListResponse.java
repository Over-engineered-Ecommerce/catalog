package com.overengineeredecommerce.transport.dto;

import com.overengineeredecommerce.transport.HttpResponse;

import java.util.List;

public class CategoryListResponse implements HttpResponse {
    private List<CategoryResponseDto> categories;

    public CategoryListResponse(List<CategoryResponseDto> categories) {
        this.categories = categories;
    }
}
