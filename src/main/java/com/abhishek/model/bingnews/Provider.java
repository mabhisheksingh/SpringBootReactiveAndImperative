package com.abhishek.model.bingnews;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
class Provider{
    private String _type;
    private String name;
    @JsonProperty(value = "image")
    private BingImage image;

}