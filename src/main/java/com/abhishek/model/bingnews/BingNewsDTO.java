package com.abhishek.model.bingnews;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BingNewsDTO {
    @NotNull
    @JsonProperty(value = "_type")
    private String type;
    @NotNull
    private String webSearchUrl;
    @JsonProperty(value = "value")
    private List<BingNews> bingNews;
}

