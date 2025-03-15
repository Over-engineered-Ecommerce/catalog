package com.overengineeredecommerce.transport.dto;

import com.overengineeredecommerce.transport.HttpResponse;

import java.util.List;

public class ProductListResponse implements HttpResponse {
    private List<ProductResponseDto> products;
}
