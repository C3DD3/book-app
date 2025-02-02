package com.example.bookApp.mapper;

import com.example.bookApp.client.models.GoogleBookItem;
import com.example.bookApp.entity.dtos.BookSearchResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GoogleBookMapperService {

    private final ModelMapper modelMapper;

    public GoogleBookMapperService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BookSearchResponseDto mapToBookDto(GoogleBookItem googleBookItem) {
        BookSearchResponseDto dto = new BookSearchResponseDto();

        modelMapper.map(googleBookItem.getVolumeInfo(), dto);

        if (googleBookItem.getVolumeInfo().getAuthors() != null) {
            dto.setAuthorNameSurname(String.join(", ", googleBookItem.getVolumeInfo().getAuthors()));
        }

        if (googleBookItem.getVolumeInfo().getIndustryIdentifiers() != null) {
            googleBookItem.getVolumeInfo().getIndustryIdentifiers().stream()
                    .filter(identifier -> "ISBN_13".equals(identifier.getType()))
                    .findFirst()
                    .ifPresent(isbn -> dto.setISBN13(isbn.getIdentifier()));
        }

        dto.setPublisherName(googleBookItem.getVolumeInfo().getPublisher());

        if (googleBookItem.getSaleInfo() != null && googleBookItem.getSaleInfo().getListPrice() != null) {
            dto.setPrice(googleBookItem.getSaleInfo().getListPrice().getAmount());
        }

        return dto;
    }
}
