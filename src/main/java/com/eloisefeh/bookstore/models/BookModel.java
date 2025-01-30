package com.eloisefeh.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_books")
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, length = 50)
    private String name;

    @Column(name = "totalQuantity", nullable = false)
    private String totalQuantity;

    @Column(name = "releaseDate")
    private String releaseDate;

    // cria relacimento e a coluna no banco que armazena a referência
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisherId", nullable = false)
    private PublisherModel publisher;

}
