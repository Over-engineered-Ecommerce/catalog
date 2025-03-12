package com.overengineeredecommerce.transport.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    public static final String PLEASE_INFORM_THE_PRODUCT = "Please inform the Product";
    public static final String NAME_MESSAGE = PLEASE_INFORM_THE_PRODUCT + " name.";
    public static final String BRAND_MESSAGE = PLEASE_INFORM_THE_PRODUCT + " brand.";
    public static final String EAN_MESSAGE = PLEASE_INFORM_THE_PRODUCT + " ean.";


    @NotBlank(message = NAME_MESSAGE)
    @NotNull(message = NAME_MESSAGE)
    @Size(min = 3, max = 100, message = "Please inform a name between 3 and 100 characters.")
    private String name;

    @NotBlank(message = PLEASE_INFORM_THE_PRODUCT + BRAND_MESSAGE)
    @NotNull(message = PLEASE_INFORM_THE_PRODUCT + BRAND_MESSAGE)
    private String brand;

    @NotBlank(message = PLEASE_INFORM_THE_PRODUCT + EAN_MESSAGE)
    @NotNull(message = PLEASE_INFORM_THE_PRODUCT + EAN_MESSAGE)
    private String ean;

    private Map<String, String> details;
}
