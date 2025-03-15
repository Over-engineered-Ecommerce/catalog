package com.overengineeredecommerce.transport.dto;


import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Data transfer object for category requests
 * <p> This class is responsible for holding the data sent by the client
 * when creating a new category.
 * </p>
 */
@Data
public class CategoryRequestDto {

    @Size(min = 3, max = 100, message = "Please inform a name between 3 and 100 characters.")
    private String name;

    public CategoryRequestDto(String name) {
        this.name = name;
    }

}
