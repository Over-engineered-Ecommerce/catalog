package com.overengineeredecommerce.transport.dto;

import com.overengineeredecommerce.transport.HttpResponse;

import java.util.UUID;

public record CategoryResponseDto(UUID categoryId, String name) implements HttpResponse { }
