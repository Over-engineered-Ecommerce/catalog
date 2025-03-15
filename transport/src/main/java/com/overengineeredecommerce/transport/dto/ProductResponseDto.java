package com.overengineeredecommerce.transport.dto;

import com.overengineeredecommerce.domain.entity.Category;
import com.overengineeredecommerce.transport.HttpResponse;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public record ProductResponseDto(UUID productId, String name, String brand, String ean, Set<Category> categories, Map<String, String> details) implements HttpResponse { }