package com.mutsa.hw5bookloan.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookinfo_id")
    private Long bookInfoId;

    private Long isbn;

    private String title;

    private String author;

    private String publisher;
}