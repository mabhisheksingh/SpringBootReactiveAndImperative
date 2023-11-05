package com.abhishek.model.bingnews;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BingNews {
    private String _type;
    private String name;
    private String description;
    private Date datePublished;
    private URL url;
    @JsonProperty(value = "image")
    private BingImage bingImage;
    @JsonProperty(value = "provider")
    private Provider[] provider;
}

