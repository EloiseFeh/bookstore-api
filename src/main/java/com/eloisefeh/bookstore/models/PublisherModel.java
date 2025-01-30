package com.eloisefeh.bookstore.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;


import java.util.HashSet;
import java.util.Set;


@Data   
@Entity
@Table(name = "tb_publisher")
public class PublisherModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    @Column(name = "phone", unique = true, nullable = false, length = 50)
    private String phone;

    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;

    @Column(name = "site", unique = true, length = 50)
    private String site;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY, orphanRemoval = true )
    private Set<BookModel> books = new HashSet<>();

}
