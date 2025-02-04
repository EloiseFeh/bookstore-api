package com.eloisefeh.bookstore.book.dtos;

public record BookRecordDto(String name,
                            String totalQuantity,
                            String releaseDate,
                            Long publisherId) {
}
