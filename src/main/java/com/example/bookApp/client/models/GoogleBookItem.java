package com.example.bookApp.client.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleBookItem {
    private VolumeInfo volumeInfo;
    private SaleInfo saleInfo;
}
