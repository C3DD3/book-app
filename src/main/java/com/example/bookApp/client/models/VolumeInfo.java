package com.example.bookApp.client.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VolumeInfo {
    private String title;
    private List<String> authors;
    private String publisher;
    private String publishedDate;
    private double price;
    private String ISBN13;
    private String authorNameSurname;
    private ArrayList<IndustryIdentifier> industryIdentifiers;

}
