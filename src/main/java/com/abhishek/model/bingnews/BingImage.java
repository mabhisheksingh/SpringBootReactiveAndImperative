package com.abhishek.model.bingnews;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BingImage {

    @NotNull
    @JsonProperty(value = "_type")
    private String type;
    @JsonProperty(value = "thumbnail")
    private Thumbnail thumbnail;
    private Boolean isLicensed;
}
