package com.npst.config.server.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualListDto {
    @NotNull(message = "Field - Application Name, can't be null")
    private String applicationName;
    @NotNull(message = "Field - Profile, can't be null")
    private String profile;
    @NotNull(message = "Field - Label, can't be null")
    private String label;
}
