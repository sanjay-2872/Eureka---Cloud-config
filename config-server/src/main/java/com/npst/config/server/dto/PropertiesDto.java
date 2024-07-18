package com.npst.config.server.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertiesDto {
    @NotNull(message = "Field - id, can't be null")
    private String id;
    @NotNull(message = "Field - application, can't be null")
    private String application;
    @NotNull(message = "Field - profile, can't be null")
    private String profile;
    @NotNull(message = "Field - label, can't be null")
    private String label;
    @NotNull(message = "Field - key, can't be null")
    private String key;
    @NotNull(message = "Field - value, can't be null")
    private String value;
    @NotNull(message = "Field - createdBy, can't be null")
    private String createdBy;
}
