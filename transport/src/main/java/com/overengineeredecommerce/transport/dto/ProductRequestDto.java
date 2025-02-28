package com.overengineeredecommerce.transport.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductRequestDto {

    public static final String PLEASE_INFORM_THE_PRODUCT = "Please inform the Product";
    public static final String NAME = " name.";
    public static final String BRAND = " brand.";
    public static final String EAN = " ean.";


    @NotBlank(message = PLEASE_INFORM_THE_PRODUCT + NAME)
    @NotNull(message = PLEASE_INFORM_THE_PRODUCT + NAME)
    @Size(min = 3, max = 100, message = "Please inform a name between 3 and 100 characters.")
    private String name;

    @NotBlank(message = PLEASE_INFORM_THE_PRODUCT + BRAND)
    @NotNull(message = PLEASE_INFORM_THE_PRODUCT + BRAND)
    private String brand;

    @NotBlank(message = PLEASE_INFORM_THE_PRODUCT + EAN)
    @NotNull(message = PLEASE_INFORM_THE_PRODUCT + EAN)
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
