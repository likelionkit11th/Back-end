package com.mutsa.hw5bookloan.Domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_info_id")
    private BookInfo bookInfo;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    public static Book newBook(BookInfo bookInfo) {
        return new Book(null, bookInfo, BookStatus.NONE);
    }

    public void setLOANED() {
        this.bookStatus = BookStatus.LOANED;
    }

    public void setNONE() {
        this.bookStatus = BookStatus.NONE;
    }
}
