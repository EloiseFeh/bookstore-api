package com.eloisefeh.bookstore.book.dtos;

import lombok.Data;

@Data
public class BookResponseDto {
    private Long id;
    private String name;
    private String totalQuantity;
    private String releaseDate;
    private Long publisherId;

    public BookResponseDto(Long id, String name, String totalQuantity, String releaseDate, Long publisherId) {
        this.id = id;
        this.name = name;
        this.totalQuantity = totalQuantity;
        this.releaseDate = releaseDate;
        this.publisherId = publisherId;
    }

}
