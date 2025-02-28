package com.overengineeredecommerce.transport.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductRequestDto {

    @NotBlank(message = "Please inform the category name.")
    @NotNull(message = "Please inform the category name.")
    @Size(min = 3, max = 100, message = "Please inform a name between 3 and 100 characters.")
    private String name;

    @NotBlank(message = "Please inform the Product brand.")
    @NotNull(message = "Please inform the Product brand.")
    private String brand;

    @NotBlank(message = "Please inform the Product ean.")
    @NotNull(message = "Please inform the Product ean.")
    private String ean;


    public ProductRequestDto() {}

    public ProductRequestDto(String name, String brand, String ean) {
        this.name = name;
        this.brand = brand;
        this.ean = ean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }
}
