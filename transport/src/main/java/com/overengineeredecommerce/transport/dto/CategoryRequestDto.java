package com.overengineeredecommerce.transport.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoryRequestDto {

    @NotBlank(message = "Please inform the category name.")
    @NotNull(message = "Please inform the category name.")
    @Size(min = 3, max = 100, message = "Please inform a name between 3 and 100 characters.")
    private String name;

    public CategoryRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
