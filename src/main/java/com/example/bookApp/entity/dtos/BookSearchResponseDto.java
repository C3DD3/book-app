package com.example.bookApp.entity.dtos;


import com.example.bookApp.client.models.GoogleBookItem;
import com.example.bookApp.mapper.GoogleBookMapperService;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookSearchResponseDto {
    private String title;
    private String price;
    private String ISBN13;
    private String publisherName;
    private String authorNameSurname;

    public static List<BookSearchResponseDto> fromList(List<GoogleBookItem> googleBookItems, GoogleBookMapperService modelMapper) {
        List<BookSearchResponseDto> bookDTOs = new ArrayList<>();
        for (GoogleBookItem googleBookItem : googleBookItems) {
            bookDTOs.add(modelMapper.mapToBookDto(googleBookItem));
        }
        return bookDTOs;
    }
}