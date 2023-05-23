package com.mutsa.hw5bookloan.Domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Loan {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키 생성을 DB에 위임하는 전략
    @Column(name = "loan_id")
    private Long id;

    @Column(name = "loan_date")
    @CreatedDate
    private LocalDate loanDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    public Loan(Member member, Book book) {
        this.member = member;
        this.book = book;
        member.addLoan(this);
        book.setLOANED();
    }

    public void returnBook() {
        this.book.setNONE();
    }
}
