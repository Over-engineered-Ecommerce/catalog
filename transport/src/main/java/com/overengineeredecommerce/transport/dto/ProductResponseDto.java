package com.overengineeredecommerce.transport.dto;

import java.util.Map;
import java.util.UUID;

public record ProductResponseDto(UUID id, String name, String brand, String ean, Map<String, String> details) { }