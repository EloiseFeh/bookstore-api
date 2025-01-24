package com.eloisefeh.bookstore.dtos;

import com.eloisefeh.bookstore.models.PublisherModel;

public record BookRecordDto(String name,
                            String totalQuantity,
                            String releaseDate,
                            Long publisherId) {
}
